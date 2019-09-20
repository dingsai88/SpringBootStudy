package designpatterns.chainofresponsibility;
/**
 * ְ����ģʽ��Chain of Responsibility������:
ʹ��������л��ᴦ�����󣬴Ӷ���������ķ����ߺͽ�����֮�����Ϲ�ϵ���������������һ���������������������ݸ�����ֱ����һ����������Ϊֹ��



����:1��-������С��10�����ദ��
����:5��-������С��10�����ദ��
����:11��- ������С��20����10������
����:21��-  ���ڵ���20�Ĵ����� ����
����:20��-  ���ڵ���20�Ĵ����� ����
����:9999��-  ���ڵ���20�Ĵ����� ����


 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-2 ����10:04:43
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-6-2 ����9:58:42
	 * @param args
	 */
	public static void main(String[] args) {


		HandlerImpl1 handler1=new HandlerImpl1();
		HandlerImpl2 handler2=new HandlerImpl2();
		HandlerImpl3 handler3=new HandlerImpl3();
        handler1.setHandler(handler2);
        handler2.setHandler(handler3);
        int[] request={1,5,11,21,20,9999};
		for(int i:request){
			System.out.print("����:"+i+"��-");
			handler1.handlerRequest(i);
		}
	}

}
