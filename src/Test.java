import com.skyley.skstack_ip.api.SKDevice;



public class Test {

	public static void main(String args[]) {
		SKDevice device1 = new SKDevice();
		device1.connect("COM3");

		SKDevice device2 = new SKDevice();
		device2.connect("COM4");

		/*
		RegisterTest rt = new RegisterTest(device1);
		rt.doTest();

		TableTest tt = new TableTest(device1);
		tt.doTest();

		ScanTest st = new ScanTest(device1, device2);
		st.doTest();

		PanaTest pt = new PanaTest(device1, device2);
		pt.doTest();
		*/

		UdpTest udptest = new UdpTest(device1, device2);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 300; i++) {
			sb.append("TEST");
		}
		udptest.doTest(sb.toString(), 10, 1000); // (送信文字列, 送信試行回数, 送信間隔[msec])

		/*
		TcpTest tcptest = new TcpTest(device1, device2);
		tcptest.doTest("TCP_SEND-TEST^", 10);
		*/

		device1.close();
		device2.close();

		System.out.println("Done!");
	}

}