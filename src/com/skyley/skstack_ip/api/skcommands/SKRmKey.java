﻿/****
Copyright (c) 2015, Skyley Networks, Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
* Redistributions of source code must retain the above copyright notice, 
  this list of conditions and the following disclaimer.
* Redistributions in binary form must reproduce the above copyright notice, 
  this list of conditions and the following disclaimer in the documentation 
  and/or other materials provided with the distribution.
* Neither the name of the Skyley Networks, Inc. nor the names of its contributors 
  may be used to endorse or promote products derived from this software 
  without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL Skyley Networks, Inc. BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
****/

package com.skyley.skstack_ip.api.skcommands;


/**
* SKRMKEYコマンドに対応したクラス、SKCommandを継承
* @author Skyley Networks, Inc.
* @version 0.1
*/
public class SKRmKey extends SKCommand {
	/** 削除対象のキーインデックス */
	private short index;


	/**
	 * コンストラクタ
	 * @param index 削除対象のキーインデックス
	 */
	public SKRmKey(short index) {
		this.index = index;
	}

	/**
	 * 引数チェック
	 */
	@Override
	public boolean checkArgs() {
		// TODO 自動生成されたメソッド・スタブ
		if (index >= 0 || index <= 0xFF) {
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
		int idx = Short.toUnsignedInt(index);
		commandString = "SKRMKEY " + Integer.toHexString(idx) + "\r\n";
	}

}
