package com.skyley.skstack_ip.api.skcommands;

/**
* SKAUTOUPDコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKAutoUpd extends SKCommand {

	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	public void buildCommand() {
		// TODO 自動生成されたメソッド・スタブ
		commandString = "SKAUTOUPD\r\n";
	}

}
