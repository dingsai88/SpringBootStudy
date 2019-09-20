package designpatterns.chainofresponsibility;
/**
 * ������С��20����10������
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-2 ����9:55:12
 */
public class HandlerImpl2 extends Handler {

	@Override
	public void handlerRequest(int request) {
		if (request >= 10 && request < 20) {
			System.out.println(" ������С��20����10������");
		} else if (this.successor != null) {
			//������һ��������ִ��
			this.successor.handlerRequest(request);

		}
	}

}