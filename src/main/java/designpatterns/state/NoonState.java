package designpatterns.state;

/**
 * �����״̬
 * 
 * @author daniel
 * @version ��ʽ��
 */
public class NoonState extends State {
	@Override
	public void writeProgram(Work w) {
		if (w.getHour() < 13) {
			System.out.println("��ǰʱ��" + w.getHour() + "��,���ˣ�������");
		} else {
			w.setCurrent(new AfternoonState());
			w.writeProgram();
		}
	}

}