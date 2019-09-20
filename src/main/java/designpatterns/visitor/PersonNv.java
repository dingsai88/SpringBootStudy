package designpatterns.visitor;
/**
 * Ů����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 ����2:28:27
 */
public class PersonNv implements Person {

	@Override
	public void showTime(Visitor visitor) {
		visitor.getVersion(this);
	}

	 
	@Override
	public String getMessage() {
		//System.out.println("Ů������");
		return "Ů��";
	}
}
