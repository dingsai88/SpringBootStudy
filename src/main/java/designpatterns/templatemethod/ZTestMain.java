package designpatterns.templatemethod;
/**
ģ�巽��ģʽ������һ�������е��㷨�ĹǼܣ�����һЩ�����ӳٵ������С�ģ�巽��ʹ��������Բ��ı�һ���㷨�Ľṹ�����ض�����㷨��ĳЩ�ض����衣
�ŵ�

ͨ���ɲ������Ϊ���Ƶ����࣬ȥ�������ظ����롣
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-6-1 ����10:18:55
 */
public class ZTestMain {
	/**
	 * @author daniel
	 * @time 2016-6-1 ����10:18:00
	 * @param args
	 */
	public static void main(String[] args) {
		   System.out.println("С���ʾ��:");
		   TestPaper studentA=new TestPaperXiaoMing();
		   studentA.testQuestion1();
		   studentA.testQuestion2();
		   System.out.println("�����ʾ��:");
		   TestPaper studentB=new TestPaperXiaoHong();
		   studentB.testQuestion1();
		   studentB.testQuestion2();
	}

}
