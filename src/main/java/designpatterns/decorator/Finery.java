package designpatterns.decorator;
/**
 * װ��ģʽ����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-18 ����10:25:51
 */
public class Finery implements IPersonShow {
	// ���
	private IPersonShow compoment;

	/**
	 * װ������
	 * @author daniel
	 * @time 2016-5-18 ����9:54:46
	 * @param compoment
	 */
	public void decorate(IPersonShow compoment) {
		this.compoment = compoment;
	}

	public void show() {
		System.out.println(" Finery:show");
		if (compoment != null) {
			compoment.show();
		}
	}

}
