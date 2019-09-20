package designpatterns.chainofresponsibility;
/**
 * ���ڵ���20�Ĵ�����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-2 ����9:57:49
 */
public class HandlerImpl3 extends Handler {

	@Override
	public void handlerRequest(int request) {
		if (request >= 20 ) {
			System.out.println("  ���ڵ���20�Ĵ����� ����");
		} else if (this.successor != null) {
			//������һ��������ִ��
			this.successor.handlerRequest(request);

		}
	}

}