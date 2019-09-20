package designpatterns.flyweight;
/**
 * ��Ԫģʽ��Flyweight��:���ù�������Ч��֧�ִ���ϸ���ȵĶ���
 * 
 * �������ӵ����ͬ���ݵ�С��Ŀ���(��ķ��ڴ�),ʹ��ҹ���һ����(Ԫ��).
ͬ����ֻ��һ������

������
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-30 ����10:53:34
 */
public class ZTestMain {

	/**
	 * @author daniel
	 * @time 2016-5-30 ����10:53:24
	 * @param args
	 */
	public static void main(String[] args) {
		  WebSiteFactory f=new WebSiteFactory();
		  
		  WebSite fx=f.getWebSiteCategory("�Ƽ���");
		  fx.use();
		  WebSite fx1=f.getWebSiteCategory("������");
		  fx1.use();
		  
		  WebSite fx2=f.getWebSiteCategory("������");
		  fx2.use(); 
		  WebSite fy=f.getWebSiteCategory("����");
		  fy.use();
		  
		  WebSite fz=f.getWebSiteCategory("����");
		  fz.use();
		  
		  System.out.println("��վ��������Ϊ:"+f.getWebSiteCount());
	}

}
