package designpatterns.decorator;
 
/**
 * ��ͼ��
��̬�ظ�һ���������һЩ�����ְ�𡣾����ӹ�����˵��Decorator ģʽ������������Ϊ��
�����ԣ�


�ڲ�Ӱ���������������£��Զ�̬��͸���ķ�ʽ�������������ְ��


������Щ���Գ�����ְ��


�����ܲ�����������ķ�����������ʱ��һ������ǣ������д�����������չ��Ϊ֧��ÿһ����Ͻ��������������࣬ʹ��������Ŀ�ʱ�ը����������һ�������������Ϊ�ඨ�屻���أ����ඨ�岻�������������ࡣ


���:
��T��
 Finery:show
��ë��
 Finery:show
������Ь
 Finery:show
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-18 ����10:24:31
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-5-18 ����9:48:25
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
       //Ь
		ThuaBanXie huabanxie = new ThuaBanXie();
		//ë��
		TmaoKu maoku = new TmaoKu();
		//����
		TShirts tshirt = new TShirts();



		//����Ь��װ��
		maoku.decorate(huabanxie);
		//ë�㱻װ��
		tshirt.decorate(maoku);
		//T����װ��
		tshirt.show();
		 
	 
	}
}
