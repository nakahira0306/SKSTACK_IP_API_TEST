package com.skyley.skstack_ip.api.skcommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.skyley.skstack_ip.api.SKUtil;

/**
* SKSETHPWDコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKSetHPwd extends SKCommand {
	/** PSK登録対象のMACアドレス */
	private String macAddress;
	/** パスワード */
	private String password;

	public SKSetHPwd(String macAddress, String password) {
		this.macAddress = macAddress;
		this.password = password.toUpperCase();
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (!SKUtil.isValidLongAddress(macAddress)) {
			return false;
		}

		String regex = "^[0-9A-Z]+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(password);
		return m.find();
	}

	/**
	 * コマンド文字列組み立て
	 */
	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKSETHPWD " + macAddress + " " + password + "\r\n";
	}

}
