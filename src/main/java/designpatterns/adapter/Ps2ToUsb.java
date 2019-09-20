package designpatterns.adapter;
/**
 * �������� �ڴ�����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-25 ����11:34:21
 */
public class Ps2ToUsb implements UsbPort {
	// ps2��
	private Ps2Port ps2;

	/**
	 * ˽�л����캯��������ע��ps2
	 */
	private Ps2ToUsb() {
	}

	public Ps2ToUsb(Ps2Port ps) {
		ps2 = ps;
	}

	/**
	 * ԭ��USBʵ�ַ������ڴ˽���ת��
	 */
	@Override
	public void workWithUsb() {
		ps2.workWithPs2();
		System.out.println("ת����");
		System.out.println("USB����");
	}

}
