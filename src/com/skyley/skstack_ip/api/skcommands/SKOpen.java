package com.skyley.skstack_ip.api.skcommands;

/**
* SKOPENコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKOpen extends SKCommand {
	/** HANイニシャルモードの持続時間（単位 秒） */
	private short duration;

	/**
	 * コンストラクタ
	 * @param duration HANイニシャルモードの持続時間（単位 秒）
	 */
	public SKOpen(short duration) {
		this.duration = duration;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (duration == 0) {
			return true;
		}
		else if (duration >= 300 && duration <= 1800) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKOPEN " + Integer.toHexString(duration) + "\r\n";
	}

}
