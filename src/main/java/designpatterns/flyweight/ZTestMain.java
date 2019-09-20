package designpatterns.flyweight;

/**
 * ��Ԫģʽ��Flyweight��:���ù�������Ч��֧�ִ���ϸ���ȵĶ���
 * <p>
 * �������ӵ����ͬ���ݵ�С��Ŀ���(��ķ��ڴ�),ʹ��ҹ���һ����(Ԫ��).
 * ͬ����ֻ��һ������
 * <p>
 * ������
 *
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-30 ����10:53:34
 */
public class ZTestMain {

    /**
     * @param args
     * @author daniel
     * @time 2016-5-30 ����10:53:24
     */
    public static void main(String[] args) {
        WebSiteFactory f = new WebSiteFactory();

        WebSite fx = f.getWebSiteCategory("�Ƽ���");
        fx.use();
        WebSite fx1 = f.getWebSiteCategory("������");
        fx1.use();
        WebSite fx2 = f.getWebSiteCategory("������");
        fx2.use();
        WebSite fy = f.getWebSiteCategory("����");
        fy.use();
        WebSite fz = f.getWebSiteCategory("����");
        fz.use();
/*
        WebSite fxaa = f.getWebSiteCategory("�Ƽ���");
        fxaa.use();
        WebSite fxbb = f.getWebSiteCategory("�Ƽ���");
        fxbb.use();
        WebSite fxcc = f.getWebSiteCategory("�Ƽ���");
        fxcc.use();*/



        System.out.println("��վ��������Ϊ:" + f.getWebSiteCount());
    }

}
