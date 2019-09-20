package designpatterns.flyweight;

/**
 * ��վʵ����
 *
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-30 ����10:51:35
 */
public class WebSiteImpl extends WebSite {
    //��վ��������
    private String name = "";

    /**
     * ���캯��
     *
     * @param name
     */
    public WebSiteImpl(String name) {
        this.name = name;
    }

    /**
     * ��ʾ����
     */
    @Override
    public void use() {
        System.out.println("��վ����:" + this.name);
    }
}
