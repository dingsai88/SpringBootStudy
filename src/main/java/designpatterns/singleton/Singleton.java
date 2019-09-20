package designpatterns.singleton;
/**
 * ������new  ʡ���ж���
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-24 ����10:54:21
 */
public class Singleton {
	private static Singleton singleton = new Singleton();
    //˽�л����캯��
	private Singleton() {

	}

	//��ö���
	public static Singleton getInstance() {
		return singleton;
	}

}
