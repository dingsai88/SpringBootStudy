package designpatterns.proxy;
/**
 * ����ʵ����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-19 ����10:42:42
 */
public class SubjectImpl implements Subject {

	@Override
	public void request() {

		System.out.println("��������");
	}

}
