package designpatterns.state;
/**
 * �����״̬
 * @author daniel
 * @version ��ʽ��
 */
public class EveningState extends State {

 @Override
 public void writeProgram(Work w) {
       // �жϹ����Ƿ����
  if(w.isFinish()){
   w.setCurrent(new RestState());
   w.writeProgram();
  }else{
   if(w.getHour()<21){
    System.out.println("��ǰʱ��"+w.getHour()+"��,�Ӱ�Ŷ ƣ��������");
   }else{
    w.setCurrent(new SleepingState());
    w.writeProgram();
   }
   
  }
 }

}