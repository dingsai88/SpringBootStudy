package designpatterns.state;
/**
 * �Ӱ�ӵ� ˯��״̬
 * @author daniel
 * @version ��ʽ��
 */
public class SleepingState extends State {

 @Override
 public void writeProgram(Work w) {
  // TODO Auto-generated method stub 
  System.out.println("��ǰʱ��"+w.getHour()+",������ƣ��������");
 }

}