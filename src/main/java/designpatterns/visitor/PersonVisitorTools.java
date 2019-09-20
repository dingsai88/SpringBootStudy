package designpatterns.visitor;
 
import java.util.ArrayList;
import java.util.List;

/**
 * �۲�person�ļ�����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-11 ����2:27:03
 */
public class PersonVisitorTools {

	 /**
	  * PerSon����
	  */
	 private List<Person> elements=new ArrayList<Person>();
	 /**
	  * ����˶���
	  * @param element
	  */
	 public void add(Person element){
	  elements.add(element);
	 }
	 /**
	  * ɱ��
	  * @param element
	  */
	 public void delete(Person element){
	  elements.remove(element);
	 }
	 /**
	  * �Ŷӱ���  ����״̬�� �˵ķ�Ӧ
	  * @param visitor
	  */
	public void show(Visitor visitor) {
		for (Person e : elements) {
			e.showTime(visitor);
		}
	} 
	 
	 
}
