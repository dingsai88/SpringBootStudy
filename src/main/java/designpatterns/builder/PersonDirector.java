package designpatterns.builder;
/**
 * ������
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 ����10:20:21
 */
public class PersonDirector {

	/**
	 * ������������
	 * @author daniel
	 * @time 2016-5-23 ����10:20:28
	 * @param personBuilder
	 * @return
	 */
	public Person constructPerson(PersonBuilder personBuilder) {
		personBuilder.buildHead();
		personBuilder.buildBody();
		personBuilder.buildFoot();
		return personBuilder.buildPerson();

	}

}
