package designpatterns.state;

/**
 * �����״̬��
 * 
 * @author daniel
 * @version ��ʽ��
 */
public class AfternoonState extends State {
	@Override
	public void writeProgram(Work w) {
		if (w.getHour() < 17) {
			System.out.println("��ǰʱ��" + w.getHour() + "��,����״̬����������Ŭ����");
		} else {
			w.setCurrent(new EveningState());
			w.writeProgram();
		}
	}

}