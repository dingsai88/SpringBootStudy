package designpatterns.state;
/**
 *  ����״̬��
 * @author daniel
 * @version ��ʽ��
 */
public class Work {
 private State current;//״̬
 private double hour;//ʱ��
 private boolean finish=false;//�Ƿ����
 /**
  * ��ʼ������
  */
 public Work(){
  current=new ForenoonState();
 }
 /**
  * �����ǰԱ��״̬
  */
 public void writeProgram(){
  current.writeProgram( this);
 }
 
 /**
  * daniel_�Զ����ɵ�get����
  * 
  * @return the current
  */
 public State getCurrent() {
  return current;
 }
 /**
  * daniel_�Զ����ɵ�set����
  *  
  * @param current the current to set
  */
 public void setCurrent(State current) {
  this.current = current;
 }
 /**
  * daniel_�Զ����ɵ�get����
  * 
  * @return the hour
  */
 public double getHour() {
  return hour;
 }
 /**
  * daniel_�Զ����ɵ�set����
  *  
  * @param hour the hour to set
  */
 public void setHour(double hour) {
  this.hour = hour;
 }
 /**
  * daniel_�Զ����ɵ�get����
  * 
  * @return the finish
  */
 public boolean isFinish() {
  return finish;
 }
 /**
  * daniel_�Զ����ɵ�set����
  *  
  * @param finish the finish to set
  */
 public void setFinish(boolean finish) {
  this.finish = finish;
 }
}