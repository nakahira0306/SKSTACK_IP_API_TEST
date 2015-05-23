package com.skyley.skstack_ip.api.skevents;

/**
 * SKHanDeviceのEPANDESC文字列を解析する
 * @author Skyley Networks, Inc.
 * @version 0.1
 */
public class SKEPanDescHanParser implements SKEPanDescParser {

	/**
	 * 文字列を解析し、パラメータを格納
	 */
	@Override
	public boolean parsePanDesc(SKEPanDesc ePanDesc) {
		// TODO 自動生成されたメソッド・スタブ
		String raw = ePanDesc.getRaw();
		if (raw == null) {
			return false;
		}

		try {
			String[] ary = raw.split(",");
			int length = ary.length;
			if (length != 8 && length != 9) {
				return false;
			}

			String[] aryParam = new String[9];
			for (int i = 0; i < length; i++) {
				String[] ary2 = ary[i].trim().split(":");
				if (ary2.length != 2) {
					return false;
				}
				aryParam[i] = ary2[1];
			}

			ePanDesc.setChannel(Byte.parseByte(aryParam[0], 16));
			ePanDesc.setChannelPage(Byte.parseByte(aryParam[1], 16));
			ePanDesc.setPanID(Integer.parseInt(aryParam[2], 16));
			ePanDesc.setAddress(aryParam[3]);
			ePanDesc.setLQI(Short.parseShort(aryParam[4], 16));
			int index;
			if (length == 9) {
				ePanDesc.setPairID(aryParam[5]);
				index = 6;
			}
			else {
				index = 5;
			}
			ePanDesc.setHemsAddress(aryParam[index]);
			index++;
			if (aryParam[index].compareTo("1") == 0) {
				ePanDesc.setRelayDevice(true);
			}
			else {
				ePanDesc.setRelayDevice(false);
			}
			index++;
			if (aryParam[index].compareTo("1") == 0) {
				ePanDesc.setRelayEndPoint(true);
			}
			else {
				ePanDesc.setRelayEndPoint(false);
			}

			return true;
		}
		catch (Exception e) {
			return false;
		}

	}

}
