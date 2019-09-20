package designpatterns.visitor;
/**
 * ������
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 ����2:28:16
 */
public class PersonNan implements Person {

	@Override
	public void showTime(Visitor visitor) {


		visitor.getVersion(this);
	}

	@Override
	public String getMessage() {
		 
		return "����";
	}

}
