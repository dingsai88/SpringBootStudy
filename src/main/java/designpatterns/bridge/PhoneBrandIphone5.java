package designpatterns.bridge;
/**
 * phone5�ֻ�Ʒ��
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-26 ����12:47:00
 */
public class PhoneBrandIphone5 extends PhoneBrand {

	@Override
	public void run() {
		System.out.println("Iphone5����");
		 this.phoneSoft.run();
	}

}
