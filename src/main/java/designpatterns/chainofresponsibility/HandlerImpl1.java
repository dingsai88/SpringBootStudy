package designpatterns.chainofresponsibility;

/**
 * ������С��10������
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-2 ����9:55:12
 */
public class HandlerImpl1 extends Handler {

	@Override
	public void handlerRequest(int request) {
		if (request >= 0 && request < 10) {
			System.out.println("������С��10�����ദ��");
		} else if (this.successor != null) {
			//������һ��������ִ��
			this.successor.handlerRequest(request);

		}
	}

}
