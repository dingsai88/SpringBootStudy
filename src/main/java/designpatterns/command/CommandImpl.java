package designpatterns.command;
/**
 * ��������ʵ����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-8 ����11:11:32
 */
public class CommandImpl extends Command {
	/**
	 * ���ø��๹�췽��
	 * 
	 * @param receiver
	 */
	public CommandImpl(Receiver receiver) {
		super(receiver);
	}

	/**
	 * ִ�о��巽��
	 */
	@Override
	public void execute() {
		receiver.action();

	}

}