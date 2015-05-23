package com.skyley.skstack_ip.api.skevents;

/**
 * イベントERXUDPの文字列を解析するインタフェース
 * @author Skyley Networks, Inc.
 * @version 0.1
 */
public interface SKERxUdpParser {

	/**
	 * 文字列を解析し、パラメータを格納
	 * @param raw 対象文字列
	 * @param erxUdp パラメータを格納するSKERXUDPイベントクラス
	 * @return 解析に成功: true, 失敗:false
	 */
	public boolean parseUdp(String raw, SKERxUdp erxUdp);
}
