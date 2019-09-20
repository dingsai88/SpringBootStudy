package designpatterns.flyweight;

import java.util.Hashtable;

/**
 * ��վ����
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-30 ����10:52:25
 */
public class WebSiteFactory {
	private Hashtable<String, WebSite> flyweights = new Hashtable<String, WebSite>();

	/**
	 * �����վ����
	 * ��������ж����վʵ����
	 * @param key
	 * @return
	 */
	public WebSite getWebSiteCategory(String key) {
		if (!flyweights.containsKey(key)) {
			flyweights.put(key, new WebSiteImpl(key));
		}
		return (WebSite) flyweights.get(key);
	}

	/**
	 * �����վ��������
	 * 
	 * @return
	 */
	public int getWebSiteCount() {
		return flyweights.size();
	}
}
