package designpatterns.visitor;
/**
 * �����˶���Ľӿ�
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 ����2:28:05
 */
public interface Person {

	public void showTime(Visitor visitor);
	public String getMessage();
}
