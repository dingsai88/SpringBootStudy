package designpatterns.state;
/**
 * ��Ϣ��״̬
 * @author daniel
 * @version ��ʽ��
 */
public class RestState extends State {

 @Override
 public void writeProgram(Work w) {
  System.out.println("��ǰʱ��"+w.getHour()+",�°�ؼҡ�");

 }

}