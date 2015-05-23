package com.skyley.skstack_ip.api.skcommands;

import com.skyley.skstack_ip.api.skenums.SKDeviceModel;


/**
* SKTABLEコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKTable extends SKCommand {
	/** テーブル種類 */
	private byte mode;
	/** デバイス機種 */
	private SKDeviceModel model;

	/**
	 * コンストラクタ
	 * @param mode テーブル種類
	 */
	public SKTable(byte mode) {
		this.mode = mode;
		this.model = SKDeviceModel.GENERAL;
	}

	/**
	 * コンストラクタ
	 * @param mode テーブル種類
	 * @param model デバイス機種
	 */
	public SKTable(byte mode, SKDeviceModel model) {
		this.mode = mode;
		this.model = model;
	}

	/** 引数チェック */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		switch (model) {
			case GENERAL:
				if (mode == 1 || mode == 2 || mode == 0x0F) {
					return true;
				}
				else {
					return false;
				}

			case HAN_EXTENSION:
				if (mode == 1 || mode == 2 || mode == 9 || mode == 0x0A) {
					return true;
				}
				else {
					return false;
				}

			default:
				return false;
		}
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKTABLE " + Integer.toHexString(mode) + "\r\n";
	}

}
