package designpatterns.builder;
/**
 * �������˹��̣�������ʵ������ϸ��
 * ����
��һ�����Ӷ���Ĺ��������ı�ʾ���룬ʹͬ���Ĺ������̿��Դ�����ͬ�ı�ʾ�����������ģʽ����Ϊ������ģʽ��[1] 
ʵ�÷�Χ
1 ���������Ӷ�����㷨Ӧ�ö����ڸö������ɲ����Լ����ǵ�װ�䷽ʽʱ��
2 ��������̱�����������Ķ����в�ͬ��ʾʱ��
��ɫ
�����������ģʽ�У������¼�����ɫ��
1 builder��Ϊ����һ����Ʒ����ĸ�������ָ������ӿڡ�
2 ConcreteBuilder��ʵ��Builder�Ľӿ��Թ����װ��ò�Ʒ�ĸ������������岢��ȷ���������ı�ʾ���� �ṩһ��������Ʒ�Ľӿڡ�
3 Director������һ��ʹ��Builder�ӿڵĶ���
4 Product����ʾ������ĸ��Ӷ���ConcreteBuilder�����ò�Ʒ���ڲ���ʾ����������װ����̣�����������ɲ������࣬��������Щ����װ������ղ�Ʒ�Ľӿڡ�
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-23 ����10:26:50
 */
public class Zmain {

	/**���˱�ըͷ
���˴��ؼ�
���˴�Ű�

	 * @author daniel
	 * @time 2016-5-23 ����10:20:48
	 * @param args
	 */
	public static void main(String[] args) {

		//������-���ർ��
		PersonDirector personDirector=new PersonDirector();
		//�����࣬����ִ��������ϸ��
		Person person=personDirector.constructPerson(new PersonBuilderManImpl());
		System.out.println(""+person.getHead());
		System.out.println(""+person.getBody());
		System.out.println(""+person.getFoot());
	}

}
