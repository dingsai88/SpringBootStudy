package designpatterns.state;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

public class ZTestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// new �˹����� ����ʱ�� ���״̬
		Work w = new Work();
		w.setHour(9);
		w.writeProgram();
		w.setHour(10);
		w.writeProgram();
		w.setHour(11);
		w.writeProgram();
		w.setHour(12);
		w.writeProgram();
		w.setHour(13);
		w.writeProgram();
		w.setHour(14);
		w.writeProgram();
		w.setHour(15);
		w.writeProgram();
		w.setHour(16);
		w.writeProgram();
		w.setHour(17);
		w.writeProgram();
		w.setHour(18);
		w.writeProgram();
		w.setHour(19);
		w.writeProgram();
		w.setHour(20);
		w.writeProgram();
		w.setHour(21);
		w.writeProgram();
		w.setHour(22);
		w.writeProgram();
		w.setHour(23);
		w.writeProgram();
		w.setHour(24);
		w.writeProgram();

		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.setValidating(false);
		context.load("classpath*:applicationContext-ding*.xml");
		context.refresh();

		// String paths[] = {"applicationContext.xml"};
		// ���xml�ļ���Spring����beans���ļ���˳��һ�ᣬ·�� ������Ӧ�õĸ�Ŀ¼
		// ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);
		// MyBean myBean = (MyBean)ctx.getBean("myBeanImpl");
		// ���ʵ�����������Ҫ����ע��
		String[] names = context.getBeanNamesForType(ZTestMain.class);

		Map<String, Object> beans = new HashMap<String, Object>();

		for (String name : names) {
			System.out.println("name:" + name);
			// beans.put(name, context.getBean(name));
		}

		}

}