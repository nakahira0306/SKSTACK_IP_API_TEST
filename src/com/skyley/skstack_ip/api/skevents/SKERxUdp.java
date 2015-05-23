package com.skyley.skstack_ip.api.skevents;

import com.skyley.skstack_ip.api.skenums.SKDeviceModel;


/**
* ERXUDPイベントに対応したクラス、SKEventを実装
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKERxUdp implements SKEvent {
	/** 受信文字列のパーサー */
	private SKERxUdpParser parser;
	/** 送信元IPv6アドレス */
	private String senderIP6Address;
	/** 送信先IPv6アドレス */
	private String destIP6Address;
	/** 送信元ポート番号 */
	private int rport;
	/** 送信先ポート番号 */
	private int lport;
	/** 送信元MAC層アドレス（16進表現16文字または4文字） */
	private String senderLLA;
	/** MACフレーム暗号化フラグ */
	private boolean isSecured;
	/** 受信データ長 */
	private int dataLength;
	/** 受信データ */
	private String data;
	/** 受信RSSIレベル */
	private short rssi;

	/**
	 * コンストラクタ
	 * @param model デバイス機種
	 */
	public SKERxUdp(SKDeviceModel model) {
		switch (model) {
			case GENERAL:
				parser = new SKERxUdpGeneralParser();
				break;

			case HAN_EXTENSION:
				parser = new SKERxUdpHanParser();
				break;

			default:
				parser = new SKERxUdpGeneralParser();
				break;
		}
	}

	/**
	 * 送信元IPv6アドレスを取得
	 * @return 送信元IPv6アドレス
	 */
	public String getSenderIP6Address() {
		return senderIP6Address;
	}

	/**
	 * 送信元IPv6アドレスをセット
	 * @param ip6Address 送信元IPv6アドレス
	 */
	public void setSenderIP6Address(String ip6Address) {
		senderIP6Address = ip6Address;
	}

	/**
	 * 送信先IPv6アドレスを取得
	 * @return 送信先IPv6アドレス
	 */
	public String getDestIP6Address() {
		return destIP6Address;
	}

	/**
	 * 送信先IPv6アドレスをセット
	 * @param ip6Address 送信先IPv6アドレス
	 */
	public void setDestIP6Address(String ip6Address) {
		destIP6Address = ip6Address;
	}

	/**
	 * 送信元ポート番号を取得
	 * @return 送信元ポート番号
	 */
	public int getRPort() {
		return rport;

	}

	/**
	 * 送信元ポート番号をセット
	 * @param port 送信元ポート番号
	 */
	public void setRPort(int port) {
		this.rport = port;
	}

	/**
	 * 送信先ポート番号を取得
	 * @return 送信先ポート番号
	 */
	public int getLPort() {
		return lport;

	}

	/**
	 * 送信先ポート番号をセット
	 * @param port 送信先ポート番号
	 */
	public void setLPort(int port) {
		this.lport = port;
	}

	/**
	 * 送信元MAC層アドレスを取得
	 * @return 送信元MAC層アドレス（16進表現16文字または4文字）
	 */
	public String getSenderLLA() {
		return senderLLA;

	}

	/**
	 * 送信元MAC層アドレスをセット
	 * @param address 送信元MAC層アドレス
	 */
	public void setSenderLLA(String address) {
		senderLLA = address;
	}

	/**
	 * MACフレーム暗号化フラグを取得
	 * @return MACフレーム暗号化フラグ
	 */
	public boolean isSecured() {
		return isSecured;
	}

	/**
	 * MACフレーム暗号化フラグをセット
	 * @param flag MACフレーム暗号化フラグ
	 */
	public void setSecured(boolean flag) {
		isSecured = flag;
	}

	/**
	 * 受信データ長を取得
	 * @return 受信データ長
	 */
	public int getDataLength() {
		return dataLength;
	}

	/**
	 * 受信データ長をセット
	 * @param length 受信データ長
	 */
	public void setDataLength(int length) {
		dataLength = length;
	}

	/**
	 * 受信データを取得
	 * @return 受信データ
	 */
	public String getData() {
		return data;
	}

	/**
	 * 受信データをセット
	 * @param data 受信データ
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 受信RSSIレベルを取得
	 * @return 受信RSSIレベル
	 */
	public short getRSSI() {
		return rssi;
	}

	/**
	 * 受信RSSIレベルをセット
	 * @param rssi 受信RSSIレベル
	 */
	public void setRSSI(short rssi) {
		this.rssi = rssi;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		return parser.parseUdp(raw, this);
	}

}
