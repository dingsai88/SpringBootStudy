package designpatterns.observer;

import java.util.Scanner;

/**
 * �۲���ģʽ(����-����):������һ��һ�Զ��������ϵ���ö���۲��߶���ͬʱ����ĳһ���������������������״̬�����仯ʱ����֪ͨ���й۲��߶���ʹ�����ܹ��Զ������Լ���
 * 
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-18 ����4:01:40
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-6-18 ����3:57:10
	 * @param args
	 */
	public static void main(String[] args) {
		// ����������
		SubjectConcrete subjectConcrete = new SubjectConcrete();
		// ���� ������
		subjectConcrete.addObserver(new ObserverConcrete(subjectConcrete, "��"));
		subjectConcrete.addObserver(new ObserverConcrete(subjectConcrete, "��"));
		subjectConcrete.addObserver(new ObserverConcrete(subjectConcrete, "rod johnson"));
		// �����߸��·�����Ϣ ���ҷ���������
		subjectConcrete.setAgPrice(10);
		// subjectConcrete.Notify();
		while (true) {
			System.out.println("\n\n����������¼۸�");
			Scanner scanner = new Scanner(System.in);
			String str = scanner.nextLine();
			if (str.equals("exit")) {
				System.out.println("����");
				break;
			}
			System.out.println("������ļ۸�Ϊ:" + str);
			subjectConcrete.setAgPrice(Double.valueOf(str));
		}

	}

}
