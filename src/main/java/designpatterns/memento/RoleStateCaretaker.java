package designpatterns.memento;
/**
 * ��Ϸ����״̬������
 * @author daniel
 *
 */
public class RoleStateCaretaker {
	 //��Ϸ����״̬
	 private RoleStateMemento memento;

	 /**
	  * @return the memento
	  */
	 public RoleStateMemento getMemento() {
	  return memento;
	 }

	 /**
	  * @param memento the memento to set
	  */
	 public void setMemento(RoleStateMemento memento) {
	  this.memento = memento;
	 }
}
