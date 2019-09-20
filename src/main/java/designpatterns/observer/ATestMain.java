package designpatterns.observer;

import java.util.Scanner;

/**
 * �۲���ģʽ(����-����):������һ��һ�Զ��������ϵ���ö���۲��߶���ͬʱ����ĳһ���������������������״̬�����仯ʱ����֪ͨ���й۲��߶���
 * ʹ�����ܹ��Զ������Լ���
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 ����4:01:28
 */
public class ATestMain {

	/**
	 * @author daniel
	 * @time 2016-6-18 ����4:00:56
	 * @param args
	 */
	public static void main(String[] args) {
		AgPrice ag = new AgPrice(7.5);
		AgObserver linYiFu = new AgObserver("�۲��� �����");
		AgObserver song = new AgObserver("�۲��� �κ��");
		AgObserver lang = new AgObserver("�۲��� ����ƽ");
		ag.addObserver(linYiFu);
		ag.addObserver(song);
		ag.addObserver(lang);
		// System.out.println(ag);
		// ag.setPrice(7.8);
		// System.out.println(ag);
		while (true) {
			System.out.println("\n\n����������¼۸�");
			Scanner scanner = new Scanner(System.in);
			String str = scanner.nextLine();
			if (str.equals("exit")) {
				System.out.println("����");
				break;
			}
			System.out.println("������ļ۸�Ϊ:" + str);
			ag.setPrice(Double.valueOf(str));
		}
	}
}
