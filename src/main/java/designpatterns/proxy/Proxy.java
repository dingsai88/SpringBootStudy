package designpatterns.proxy;

/**
 * ������
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-19 ����10:42:33
 */
public class Proxy implements Subject {
	private Subject subject;

	@Override
	public void request() {
		if (subject == null) {
			subject = new SubjectImpl();
		}
		subject.request();
	}

}
