package designpatterns.builder;
/**
 * ����ʵ����
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 ����10:23:28
 */
public class PersonBuilderManImpl implements PersonBuilder{
	 private Person person;
	 public PersonBuilderManImpl(){
		 person=new Person();
	 }

	@Override
	public void buildHead() {
		person.setHead("���˱�ըͷ");
	}

	@Override
	public void buildBody() {
		person.setBody("���˴��ؼ�");
		
	}

	@Override
	public void buildFoot() {
		person.setFoot("���˴�Ű�");
		
	}

	@Override
	public Person buildPerson() {
		return person;
	}

}
