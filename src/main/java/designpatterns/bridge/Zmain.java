package designpatterns.bridge;

public class Zmain {

	/**
	 * ��GOF95�����������ģʽ��ʱ��ָ��������ģʽ��������"������(Abstraction)��ʵ�ֻ�(Implementation)���ʹ�ö��߿��Զ����ر仯"����仰�������ؼ��ʣ�Ҳ���ǳ��󻯡�ʵ�ֻ������
	 * �Ž�ģʽ��Bridge���������󲿷�������ʵ�ֲ��ַ��룬ʹ���Ƕ����Զ����ر仯��
	 * @author daniel
	 * @time 2016-5-26 ����12:42:52
	 * @param args
	 */
	public static void main(String[] args) {
        //����IP5��Ʒ
		PhoneBrandIphone5 i5 = new PhoneBrandIphone5();
		//װ����Ϸ���
		i5.setPhoneSoft(new PhoneSoftGame());
		i5.run();
		//װ��ͨѶ¼
		i5.setPhoneSoft(new PhoneSoftAddressList());
		i5.run();
		

		PhoneBrandIphone4 i4 = new PhoneBrandIphone4();
		i4.setPhoneSoft(new PhoneSoftGame());
		i4.run();
		i4.setPhoneSoft(new PhoneSoftAddressList());
		i4.run();
		
		
		
	}

}
