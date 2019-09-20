package designpatterns.chainofresponsibility;

public abstract class Handler {
	/**
	 * ��������һ���̳���
	 */
	protected Handler successor;
	public void setHandler(Handler successor){
		this.successor=successor;
	}
	
	/**
	 * ��������
	 * @author daniel
	 * @time 2016-6-2 ����9:53:43
	 * @param request
	 */
	public abstract void handlerRequest(int request);

}
