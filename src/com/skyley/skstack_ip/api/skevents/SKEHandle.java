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

package com.skyley.skstack_ip.api.skevents;


/**
 * EHANDLEイベントに対応したクラス、SKEventを実装
 * @author Skyley Networks, Inc.
 * @version 0.1
*/
public class SKEHandle implements SKEvent {
	/** ハンドル番号 */
	private byte handle;
	/** 接続先IPv6アドレス */
	private String ip6Address
	;/** 接続先ポート番号 */
	private int rport;
	/** 接続元ポート番号 */
	private int lport;

	/**
	 * ハンドル番号を取得
	 * @return ハンドル番号
	 */
	public byte getHandle() {
		return handle;
	}

	/**
	 * 接続先IPv6アドレスを取得
	 * @return 接続先IPv6アドレス
	 */
	public String getIP6Address() {
		return ip6Address;
	}

	/**
	 * 接続先ポート番号を取得
	 * @return 接続先ポート番号
	 */
	public int getRPort() {
		return rport;
	}

	/**
	 * 接続元ポート番号を取得
	 * @return 接続元ポート番号
	 */
	public int getLPort() {
		return lport;
	}

	/**
	 * 受信文字列を解析、パラメータを格納
	 */
	@Override
	public boolean parse(String raw) {
		// TODO 自動生成されたメソッド・スタブ
		if (raw == null) {
			return false;
		}

		String[] ary = raw.split(" ");
		if (ary.length != 4) {
			return false;
		}

		try {
			handle = Byte.parseByte(ary[0], 16);
			ip6Address = ary[1];
			rport = Integer.parseInt(ary[2], 16);
			lport = Integer.parseInt(ary[3], 16);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}

}
