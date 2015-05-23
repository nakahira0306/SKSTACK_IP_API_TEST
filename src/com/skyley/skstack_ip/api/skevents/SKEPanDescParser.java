package com.skyley.skstack_ip.api.skevents;

/**
 * イベントEPANDESCの文字列を解析するインタフェース
 * @author Skyley Networks, Inc.
 * @version 0.1
 */
public interface SKEPanDescParser {

	/**
	 * 文字列を解析し、パラメータを格納
	 * @param ePanDesc 解析対象のEPANDESCイベントクラス
	 * @return 解析に成功:true, 失敗: false
	 */
	public boolean parsePanDesc(SKEPanDesc ePanDesc);
}
