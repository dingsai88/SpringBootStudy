package designpatterns.memento;
/**
 * ����¼ģʽ��memento�����ڲ��ƻ���װ�Ե�ǰ���£�����һ��������ڲ�״̬�����ڸö���֮�Ᵽ�����״̬�������Ժ�Ϳɽ��ö���ָ���ԭ�ȱ����״̬��
 * 
 * ���
 * 
 * ս��ʼ
��ү����100
��ү����10
��ү����10
��ү����
��ү����0
��ү����10
��ү����10
ս��ָ�
��ү����100
��ү����10
��ү����10

 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 ����3:40:06
 */
public class ZTestMain {

	/**
	 * @author daniel
	 * @time 2016-6-18 ����3:39:21
	 * @param args
	 */
	public static void main(String[] args) { // TODO Auto-generated method stub
		  KuiYe zhanshen=new KuiYe();
		  System.out.println("ս��ʼ");
		  zhanshen.showKuiYe();
		  //���ȹ�����
		  RoleStateCaretaker log=new RoleStateCaretaker();
		  //����ս��״̬
		  log.setMemento(zhanshen.saveState());
		        //ս������
		  System.out.println("��ү����");
		  zhanshen.setVit(0);
		  zhanshen.showKuiYe();
		  System.out.println("ս��ָ�");
		  //�ָ�ս��
		  zhanshen.recoveryState(log.getMemento());
		  zhanshen.showKuiYe();
		  }

}
