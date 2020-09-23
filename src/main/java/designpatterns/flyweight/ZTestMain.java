package designpatterns.flyweight;

/**
 * 享元模式（Flyweight）:运用共享技术有效地支持大量细粒度的对象。
 * <p>
 * 避免大量拥有相同内容的小类的开销(如耗费内存),使大家共享一个类(元类).
 * 同类型只有一个对象
 * <p>
 * 输出结果
 *
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-30 上午10:53:34
 */
public class ZTestMain {

    /**
     * @param args
     * @author daniel
     * @time 2016-5-30 上午10:53:24
     */
    public static void main(String[] args) {
        WebSiteFactory f = new WebSiteFactory();

        WebSite fx = f.getWebSiteCategory("科技类");
        fx.use();
        WebSite fx1 = f.getWebSiteCategory("新闻类");
        fx1.use();
        WebSite fx2 = f.getWebSiteCategory("娱乐类");
        fx2.use();
        WebSite fy = f.getWebSiteCategory("博客");
        fy.use();
        WebSite fz = f.getWebSiteCategory("博客");
        fz.use();
/*
        WebSite fxaa = f.getWebSiteCategory("科技类");
        fxaa.use();
        WebSite fxbb = f.getWebSiteCategory("科技类");
        fxbb.use();
        WebSite fxcc = f.getWebSiteCategory("科技类");
        fxcc.use();*/



        System.out.println("网站分类总数为:" + f.getWebSiteCount());
    }

}
