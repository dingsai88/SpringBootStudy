package designpatterns.state;
/**
 * ��Ϣ��״̬
 * @author daniel
 * @version ��ʽ��
 */
public class RestState extends State {

 @Override
 public void writeProgram(Work w) {
  // TODO Auto-generated method stub
  System.out.println("��ǰʱ��"+w.getHour()+",�°�ؼҡ�");

 }

}