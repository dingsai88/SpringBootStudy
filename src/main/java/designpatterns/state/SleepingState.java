package designpatterns.state;

/**
 * �Ӱ�ӵ� ˯��״̬
 *
 * @author daniel
 * @version ��ʽ��
 */
public class SleepingState extends State {

    @Override
    public void writeProgram(Work w) {
        System.out.println("\n��̬�����");
        System.out.println("��ǰʱ��" + w.getHour() + ",������ƣ��������");
    }

}