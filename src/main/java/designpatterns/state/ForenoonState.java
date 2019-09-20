package designpatterns.state;

/**
 * ����״̬��
 * 
 * @author daniel
 * @version ��ʽ��
 */
public class ForenoonState extends State {
	/**
	 * д״̬
	 */
	@Override
	public void writeProgram(Work w) {
		if (w.getHour() < 12) {
			System.out.println("��ǰʱ��" + w.getHour() + "��,���繤�������񲻴�");
		} else {
			w.setCurrent(new NoonState());
			w.writeProgram();
		}

	}

}