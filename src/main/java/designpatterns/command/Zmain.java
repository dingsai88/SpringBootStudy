package designpatterns.command;
/**
 * ����ģʽ��Command����
��һ�������װΪһ�����󣬴Ӷ�ʹ����ò�ͬ������Կͻ��˽��в��������������Ŷӻ��߼�¼������־���Լ�֧�ֿɳ����Ĳ�����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-8 ����11:13:46
 */
public class Zmain {

	/**
	 * 
	 *   //ִ�о�������  ������>������>����ʵ����>ִ����
	 * @author daniel
	 * @time 2016-6-8 ����11:12:52
	 * @param args
	 */
	public static void main(String[] args) {
		// ִ����
		Receiver r = new Receiver();
		// ����ʵ���� װ�ؾ���ִ����
		Command c = new CommandImpl(r);
		// ����������
		Invoker i = new Invoker();
		// װ��������
		i.setCommand(c);
		// ִ�о������� ������>������>����ʵ����>ִ����
		i.executeCommand();
	}

}
