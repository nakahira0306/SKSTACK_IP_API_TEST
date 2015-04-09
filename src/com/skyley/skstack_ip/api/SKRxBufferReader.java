package com.skyley.skstack_ip.api;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.skyley.skstack_ip.api.skenums.SKDeviceModel;
import com.skyley.skstack_ip.api.skenums.SKErrorCode;
import com.skyley.skstack_ip.api.skenums.SKEventType;
import com.skyley.skstack_ip.api.skevents.SKEvent;
import com.skyley.skstack_ip.api.skevents.SKEventFactory;

/**
 * 受信バッファを読み込み、必要な処理を行う。
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKRxBufferReader implements Runnable {
	/** 受信バッファ、SKReceiverと共有する */
	private BlockingQueue<String> buffer;
	/** デバイス機種 */
	private SKDeviceModel model;
	/** デバイス接続先ポートの名称（"COM3など"） */
	private String portString;
	/** "Exxx"系のイベントリスナー */
	private SKEventListener listener;
	/** 受信バッファから読み取ったコマンド応答を格納するバッファ */
	private BlockingQueue<String> response;
	 /** 読み込み処理実行中を示すフラグ */
	private boolean isRunning;

	/**
	 * コンストラクタ
	 * @param buffer 受信バッファ、SKReceiverと共有する
	 * @param model デバイス機種
	 * @param portString デバイス接続先ポートの名称（"COM3"など）
	 */
	public SKRxBufferReader(BlockingQueue<String> buffer, SKDeviceModel model, String portString) {
		this.buffer = buffer;
		this.model = model;
		this.portString = portString;
		response = new ArrayBlockingQueue<String>(64);
		isRunning = false;
	}

	/**
	 * 登録されている"Exxxx"系のイベントリスナーを取得
	 * @return "Exxxx"系のイベントリスナー、SKEventListenerを実装したクラス
	 */
	public SKEventListener getSKEventListener() {
		return listener;
	}

	/**
	 * "Exxxx"系のイベントリスナーを登録
	 * @param listener "Exxxx"系のイベントリスナー、SKEventListenerを実装したクラス
	 */
	public void setSKEventListener(SKEventListener listener) {
		this.listener = listener;
	}

	/**
	 * "Exxxx"系のイベントリスナー登録を解除
	 */
	public void removeSKEventListener() {
		this.listener = null;
	}

	/**
	 * bufferから読み取った応答文字列を取得
	 * @param numOfLine 応答の行数
	 * @param timeout タイムアウト（単位 ミリ秒）
	 * @return 応答文字列を格納した配列（1行1要素）、取得できなかった場合はnull
	 */
	public String[] getResponse(int numOfLine, long timeout) {
		String[] res = new String[numOfLine];

		try {
			for(int i = 0; i < numOfLine; i++) {
				res[i] = response.poll(timeout, TimeUnit.MILLISECONDS);
				if (res[i] == null) {
					return null;
				}
				else {
					for (SKErrorCode code : SKErrorCode.values()) {
						// エラーコードと合致した場合は、その時点まで読みとった内容を返す
						if (res[i].trim().startsWith(code.toString())) {
							return res;
						}
					}
				}
			}

			return res;
		}
		catch(Exception e) {
			return null;
		}
	}

	/**
	 * 読み込み処理を停止
	 */
	public void stop() {
		isRunning = false;
	}

	/**
	 * 読み込み処理を実行
	 */
	@Override
	public void run() {
		boolean isMatched;
		isRunning = true;
		String line;

		try {
			while(isRunning) {
				// bufferから1要素読み込み（1行1要素）
				line = buffer.poll(1, TimeUnit.SECONDS);
				isMatched = false;

				// タイムアウトしたらbuufer.poll()に戻る
				if (line == null) {
					continue;
				}

				// 空のときはbuffer.poll()に戻る
				if (line == "") {
					continue;
				}

				// SKコマンドのエコーバックは無視、buffer.poll()に戻る
				if (line.startsWith("SK")) {
					continue;
				}

				// "OK", "FAIL ER10"などのエラーコードであれば、respoonseに格納
				// responseの内容はgetResponse()を通してSKDeviceに読み取られる
				for(SKErrorCode code : SKErrorCode.values()) {
					if(line.startsWith(code.toString())) {
						response.put(line);
						isMatched = true;
						continue;
					}
				}

				// エラーコードと一致した場合はbuffer.poll()に戻る
				if (isMatched) {
					continue;
				}

				// "ESREG", "ERXUDP"など"Exxxx"系イベントと判定すると、responseに格納、またはlistnerに通知
				for(SKEventType type : SKEventType.values()) {
					if(line.startsWith(type.name())) {
						// コマンド応答として扱う"Exxx"のときはresponseに格納
						if(type.isResponse()) {
							response.put(line);
						}
						else if (listener != null) {
							SKEventFactory ef = new SKEventFactory();
							SKEvent event = ef.createSKEvent(type, model, buffer);
							// イベントとして扱い、文字列を解析できた場合は、listenerに通知
							if(event.parse(line)) {
								listener.eventNotified(portString, type, event);
							}
						}

						isMatched = true;
						continue;
					}
				}

				// "Exxxx"系イベントと一致した場合はbuffer.poll()に戻る
				if (isMatched) {
					continue;
				}

				// SKコマンド、エラーコード、"Exxxx"以外の文字列をresponseに格納
				response.put(line);
			}
		}
		catch(Exception e) {
			isRunning = false;
			e.printStackTrace();
		}
	}
}