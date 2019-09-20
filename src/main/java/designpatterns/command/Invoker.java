package designpatterns.command;
/**
 * �������
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-8 ����11:12:33
 */
public class Invoker {
	private Command command;

	/**
	 * @param command
	 *            the command to set
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * ����������ִ�з���
	 */
	public void executeCommand() {
		command.execute();
	}

}
