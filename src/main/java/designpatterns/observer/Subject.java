package designpatterns.observer;
import java.util.ArrayList;
import java.util.List;

/**
 * �����߳�����
 * @author daniel
 *
 */
abstract class Subject {
      //�������б�
 private List<Observer> observers=new ArrayList<Observer>();
 /**
  * ��Ӷ�����
  * @param observer
  */
 public void addObserver(Observer observer){
  observers.add(observer);
 }
 /**
  * ɾ��������
  * @param observer
  */
 public void deleteObserver(Observer observer){
  observers.remove(observer);
 }
 /**
  * ���������ж�����
  */
 public void Notify(){
  for(Observer observer : observers){
   observer.update();
  }  
 } 
}