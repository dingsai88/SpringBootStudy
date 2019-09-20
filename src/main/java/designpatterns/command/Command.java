package designpatterns.command;

/**
 * ���������
 * 
 * @author daniel
 * 
 */
abstract class Command {
	// ִ��������
	protected Receiver receiver;

	public Command(Receiver receiver) {
		this.receiver = receiver;
	}

	// ����ִ�����ִ�з���
	abstract public void execute();
}