package designpatterns.facade;
/**
 * ���ģʽ(Facade):Ϊ��ϵͳ�е�һ��ӿ��ṩһ��һ�µĽ��棬��ģʽ������һ���߲�ӿڣ�����ӿ�ʹ��ϵͳ��������ʹ�á�
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-12 ����3:02:36
 */
public class ZMainTest {

	/**
	 * ���ӵ��߼���װΪһ���򵥵Ľӿ�
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Facade facade = new Facade();
		facade.methodA();
		System.out.println("\n\n");
		facade.methodB();
	}

}