package designpatterns.bridge;
/**
 * iphone4�ֻ�Ʒ��
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-26 ����12:46:49
 */
public class PhoneBrandIphone4 extends PhoneBrand {

	@Override
	public void run() {
		System.out.println("Iphone4����");
		 this.phoneSoft.run();
	}

}
