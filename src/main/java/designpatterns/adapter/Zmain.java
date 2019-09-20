package designpatterns.adapter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * ϣ������һЩ�ִ���࣬���ǽӿ����븴�û���Ҫ��һ�¡�
��ʵ������ģʽ�е�����֮�٣���ǰ����Ƶ�ʱ�����ǾͲ�Ӧ�ÿ���������ģʽ����Ӧ�ÿ���ͨ���ع�ͳһ�ӿڡ�

������ģʽ����һ����Ľӿ�ת���ɿͻ�ϣ��������һ���ӿڡ�adapterģʽʹ��ԭ�����ڽӿڲ����ݶ�����һ��������Щ�����һ������
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-25 ����11:35:22
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-5-25 ����11:31:23
	 * @param args
	 */
	public static void main(String[] args) {
		//ԭ��ps2ʵ����
		Ps2Port ps2=new Ps2Port(){
			@Override
			public void workWithPs2() {
				System.out.println("PS2����");
			}			
		};

		Ps2ToUsb to=new Ps2ToUsb(ps2);
		//ִ������ת��
		to.workWithUsb();


		//Ӧ��
		List list= Arrays.asList("2","1");
		list.remove(1);
		
	}

}
