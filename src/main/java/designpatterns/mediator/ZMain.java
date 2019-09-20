package designpatterns.mediator;
/**
 * �н���ģʽ��Mediator������һ���н��������װһϵ�еĶ��󽻻����н���ʹ��������Ҫ��ʾ���໥���ã��Ӷ�ʹ�������ɢ�����ҿ��Զ����ĸı�����֮��Ľ�����
 * 
 * ���
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-12 ����12:27:40
 */
public class ZMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
 
		UnitedNationsSecurityCouncil UNSC = new UnitedNationsSecurityCouncil();
		USA c1 = new USA(UNSC);
		Iraq c2 = new Iraq(UNSC);
		UNSC.setColleague1(c1);
		UNSC.setColleague2(c2);

		c1.declare("����˵ ��Ҫ�Է�");
		c2.declare("������˵ û�����");

	}
}
