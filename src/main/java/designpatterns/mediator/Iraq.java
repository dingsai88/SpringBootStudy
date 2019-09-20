package designpatterns.mediator;
/**
 * ������ ����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-12 ����10:03:28
 */
public class Iraq extends Country {
	 /**
	  * ���캯��
	  * @param mediator
	  */
	 public Iraq(UnitedNations mediator){
	  //���ø��๹�캯��
	  super(mediator);
	 }
	 /**
	  * ����
	  * @param message
	  */
	 public void declare(String message){
	  mediator.declare(message, this);
	 }
	 /**
	  * �����Ϣ
	  * @param message
	  */
	 public void getMessage(String message){
	  System.out.println("������öԷ���Ϣ:"+message);
	 }
	}