package designpatterns.visitor;
/**
 * �۲�������
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 ����2:28:47
 */
public class VisitorSaNiao implements Visitor {

	@Override
	public void getVersion(Person person) {
	 if(person instanceof PersonNan){
		System.out.println( person.getMessage()+"վ��");
	 }
	 if(person instanceof PersonNv){
			System.out.println( person.getMessage()+"����");
		 }

	}

}
