package designpatterns.factory;
/**
 * ����ģʽ��������õ�ʵ��������ģʽ�ˣ����ù�����������new������һ��ģʽ��
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 ����4:54:37
 */
public class ZTestMain {

	/**
	 * ������
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
 		Operation oper;
		oper = OperationFactory.createOperate('+');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

		// ���Լ�
		oper = OperationFactory.createOperate('-');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

		// ���Գ�
		oper = OperationFactory.createOperate('*');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

		oper = OperationFactory.createOperate('/');
		oper.setNumberA(4);
		oper.setNumberB(2);
		System.out.println(oper.getResult());

	}

}
