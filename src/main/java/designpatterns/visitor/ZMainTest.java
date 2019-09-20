package designpatterns.visitor;
/**
 * ������ģʽ����Visitor��,��ʾһ��������ĳ������ṹ�ĸ�Ԫ�صĲ�������ʹ������ڲ��ı�Ԫ�ص����ǰ���¶�����������ЩԪ�ص��²�����
 * 
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 ����4:37:01
 */
public class ZMainTest {

	/**
	 * @author daniel
	 * @time 2016-5-11 ����2:30:19
	 * @param args
	 */
	public static void main(String[] args) {
		/**
		 * �۲��߼���
		 */
		PersonVisitorTools tools = new PersonVisitorTools();
		tools.add(new PersonNan());
		tools.add(new PersonNv());

		// �۲���ȥϴ�ּ�
		Visitor visitor = new VisitorSaNiao();
		tools.show(visitor);

		// �۲��˷��
		visitor = new VisitorSaPo();
		tools.show(visitor);
	}

}
