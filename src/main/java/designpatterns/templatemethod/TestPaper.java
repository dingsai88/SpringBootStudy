package designpatterns.templatemethod;

/**
 * �ʾ�ģ�� �ʹ���ģ��
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-1 ����10:15:08
 */
public abstract class TestPaper {

	/**
	 * ��Ŀһ
	 * 
	 * @author daniel
	 * @time 2016-6-1 ����10:15:25
	 */
	public void testQuestion1() {
		System.out.println("��Ŀһ");
		System.out.println("�𰸣�" + getAnswer1());
	}

	/**
	 * ��һ
	 * 
	 * @author daniel
	 * @time 2016-6-1 ����10:15:31
	 * @return
	 */
	protected String getAnswer1() {

		return "";
	}

	/**
	 * ��Ŀ��
	 * 
	 * @author daniel
	 * @time 2016-6-1 ����10:15:44
	 */
	public void testQuestion2() {
		System.out.println("��Ŀ��");
		System.out.println("�𰸣�" + getAnswer2());
	}

	/**
	 * �𰸶�
	 * 
	 * @author daniel
	 * @time 2016-6-1 ����10:15:51
	 * @return
	 */
	protected String getAnswer2() {
		return "";
	}
}
