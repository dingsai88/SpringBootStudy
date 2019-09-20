package designpatterns.builder;
/**
 * ���˵Ľӿ�
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 ����10:23:01
 */
public interface PersonBuilder {

	void buildHead();

	void buildBody();

	void buildFoot();

	Person buildPerson();
}