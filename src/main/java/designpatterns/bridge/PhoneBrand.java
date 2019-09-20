package designpatterns.bridge;
/**
 * �ֻ�Ʒ�Ƴ�����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-26 ����11:52:09
 */
public abstract class PhoneBrand {
/**
 * �ֻ����
 */
	protected PhoneSoft phoneSoft ;
	public void setPhoneSoft(PhoneSoft phoneSoft){
		this.phoneSoft=phoneSoft;
	}
	/**
	 * �������
	 * @author daniel
	 * @time 2016-5-26 ����11:52:03
	 */
	public abstract void run(); 
	
}
