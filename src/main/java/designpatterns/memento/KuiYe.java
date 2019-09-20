package designpatterns.memento;

/**
 * ��ү��
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 ����3:27:20
 */
public class KuiYe {
	// ����
	private Integer vit;
	// ������
	private Integer atk;
	// ������
	private Integer def;

	/**
	 * �����ʼֵ
	 */
	public KuiYe() {
		this.vit = 100;
		this.atk = 10;
		this.def = 10;
	}

	/**
	 * չʾ��������
	 * @author daniel
	 * @time 2016-6-18 ����3:35:47
	 */
	public void showKuiYe() {
		System.out.println("��ү����" + this.vit);
		System.out.println("��ү����" + this.atk);
		System.out.println("��ү����" + this.def);
	}
	
	
	 /**
	  * ����ս�����
	  * @return
	  */
	 public RoleStateMemento saveState(){
	  
	  return new RoleStateMemento(this.getVit(),this.getAtk(),this.getDef());
	 }
	 /**
	  * �ָ���ү״̬
	  * @param memento
	  */
	 public void recoveryState(RoleStateMemento memento){
	  this.vit=memento.getVit();
	  this.atk=memento.getAtk();
	  this.def=memento.getDef();
	 }

	public Integer getVit() {
		return vit;
	}

	public void setVit(Integer vit) {
		this.vit = vit;
	}

	public Integer getAtk() {
		return atk;
	}

	public void setAtk(Integer atk) {
		this.atk = atk;
	}

	public Integer getDef() {
		return def;
	}

	public void setDef(Integer def) {
		this.def = def;
	}
	 
	
	
	
	
	
	
	
	
}
