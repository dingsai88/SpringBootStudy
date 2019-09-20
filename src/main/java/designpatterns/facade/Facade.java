package designpatterns.facade;
/**
 * �����
 * @author daniel
 *
 */
public class Facade {
 private SystemOne one;
 private SystemTwo two;
 private SystemThree three;
 private SystemFour four;
 /**
  * ���캯����ʼ������
  */
 public Facade(){
  one =new SystemOne();
  two =new SystemTwo();
  three=new SystemThree();
  four=new SystemFour();
 }

 /**
  * 1 2 4��ķ�������
  */
 public void methodA(){
  one.methodOne();
  two.methodTwo();
  four.methodFour();
 }
 /**
  * 123��ķ�������
  */
 public void methodB(){
  one.methodOne();
  two.methodTwo();
  three.methodThree();
 }
}