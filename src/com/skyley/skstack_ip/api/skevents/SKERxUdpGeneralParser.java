package com.skyley.skstack_ip.api.skevents;

/**
 * SKDeviceのERXUDP文字列を解析する
 * @author Skyley Networks, Inc.
 * @version 0.1
 */
public class SKERxUdpGeneralParser implements SKERxUdpParser {

	/**
	 * 文字列を解析し、パラメータを格納
	 */
	@Override
	public boolean parseUdp(String raw, SKERxUdp erxUdp) {
		// TODO 自動生成されたメソッド・スタブ
		if (raw == null) {
			return false;
		}

		try {
			String[] ary = raw.split(" ");
			if (ary.length != 9) {
				return false;
			}

			erxUdp.setSenderIP6Address(ary[1]);
			erxUdp.setDestIP6Address(ary[2]);
			erxUdp.setRPort(Integer.parseInt(ary[3], 16));
			erxUdp.setLPort(Integer.parseInt(ary[4], 16));
			erxUdp.setSenderLLA(ary[5]);
			if (ary[6].compareTo("1") == 0) {
				erxUdp.setSecured(true);
			}
			else {
				erxUdp.setSecured(false);
			}
			erxUdp.setDataLength(Integer.parseInt(ary[7], 16));
			erxUdp.setData(ary[8]);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}

}
