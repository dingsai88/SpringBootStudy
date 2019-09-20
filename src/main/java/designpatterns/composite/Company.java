package designpatterns.composite;
/**
 * ��˾�����
 * @author daniel
 *
 */
public abstract class Company {

	 
	 protected String name;
	 //��˾����
	 public Company(String name){
	  this.name=name;
	 }
	 //��ӹ�˾���߲���
	 public abstract void add(Company c);
	 public abstract void remove(Company c);
	 //չʾ��˾ ��������  �㼶
	 public abstract void display(int i);
	 //չʾ����ְ��
	 public abstract void lineOfDuty();
	}