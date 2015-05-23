package com.skyley.skstack_ip.api.skevents;

import com.skyley.skstack_ip.api.skenums.SKDeviceModel;


/**
 * EPANDESCイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKEPanDesc implements SKEvent {
	/** 受信文字列のパーサー */
	private SKEPanDescParser parser;
	/** 受信文字列（"EPANDESC"に続く行を","で結合）*/
	private String raw;
	/** 発見したPANの論理チャンネル番号 */
	private byte channel;
	/** 発見したPANのチャンネルページ */
	private byte channelPage;
	/** 発見したPANのPAN ID */
	private int panID;
	/** アクティブスキャン応答元のアドレス（16進表現） */
	private String address;
	/** 受信したビーコンの受信RSSI */
	private short lqi;
	/** 相手から受信したParing ID */
	private String pairID;
	/** HEMS 64bitアドレス（16進表現） */
	private String hemsAddress;
	/** リレーデバイスモード */
	private boolean isRelayDevice;
	/** リレーデバイスエンドポイント */
	private boolean isRelayEndPoint;

	/**
	 * コンストラクタ
	 * @param raw 受信文字列（"EPANDESC"に続く行を","で結合）
	 * @param model デバイス機種
	 */
	public SKEPanDesc(String raw, SKDeviceModel model) {
		this.raw = raw;
		switch (model) {
			case GENERAL:
				parser = new SKEPanDescGeneralParser();
				break;

			case HAN_EXTENSION:
				parser = new SKEPanDescHanParser();
				break;

			default:
				parser = new SKEPanDescGeneralParser();
				break;
		}
	}

	/**
	 * 受信文字列（"EPANDESC"に続く行を","で結合）を取得
	 * @return 受信文字列（"EPANDESC"に続く行を","で結合）
	 */
	public String getRaw() {
		return raw;
	}

	/**
	 * チャンネル番号を取得
	 * @return チャンネル番号
	 */
	public byte getChannel() {
		return channel;
	}

	/**
	 * チャンネル番号をセット
	 * @param channel チャンネル番号
	 */
	public void setChannel(byte channel) {
		this.channel = channel;
	}

	/**
	 * チャンネルページを取得
	 * @return チャンネルページ
	 */
	public byte getChannelPage() {
		return channelPage;
	}

	/**
	 * チャンネルページをセット
	 * @param channelPage チャンネルページ
	 */
	public void setChannelPage(byte channelPage) {
		this.channelPage = channelPage;
	}

	/**
	 * PAN IDを取得
	 * @return PAN ID
	 */
	public int getPanID() {
		return panID;
	}

	/**
	 * PAN IDをセット
	 * @param panID PAN ID
	 */
	public void setPanID(int panID) {
		this.panID = panID;
	}

	/**
	 * 応答元アドレスを取得
	 * @return 応答元アドレス（16進表現）
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 応答元アドレスをセット
	 * @param address 応答元アドレス
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * ビーコン受信RSSIを取得
	 * @return ビーコン受信RSSI
	 */
	public short getLQI() {
		return lqi;
	}

	/**
	 * ビーコン受信RSSIをセット
	 * @param lqi ビーコン受信RSSI
	 */
	public void setLQI(short lqi) {
		this.lqi = lqi;
	}

	/**
	 * Paring IDを取得
	 * @return Paring ID
	 */
	public String getPairID() {
		return pairID;
	}

	/**
	 * Paring IDをセット
	 * @param pairID Paring ID
	 */
	public void setPairID(String pairID) {
		this.pairID = pairID;
	}

	/**
	 * HEMS 64bitアドレスを取得
	 * @return HEMS 64bitアドレス
	 */
	public String getHemsAddress() {
		return hemsAddress;
	}

	/**
	 * HEMS 64bitアドレスをセット
	 * @param address HEMS 64bitアドレス
	 */
	public void setHemsAddress(String address) {
		hemsAddress = address;
	}

	/**
	 * リレーデバイスモードを取得
	 * @return リレーデバイスモード
	 */
	public boolean isRelayDevice() {
		return isRelayDevice;
	}

	/**
	 * リレーデバイスモードをセット
	 * @param flag リレーデバイスモード
	 */
	public void setRelayDevice(boolean flag) {
		isRelayDevice = flag;
	}

	/**
	 * リレーデバイスエンドポイントを取得
	 * @return リレーデバイスエンドポイント
	 */
	public boolean isRelayEndPoint() {
		return isRelayEndPoint;
	}

	/**
	 * リレーデバイスエンドポイントをセット
	 * @param flag リレーデバイスエンドポイント
	 */
	public void setRelayEndPoint(boolean flag) {
		isRelayEndPoint = flag;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		return parser.parsePanDesc(this);
	}

}
