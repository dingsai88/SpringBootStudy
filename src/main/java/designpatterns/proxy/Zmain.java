package designpatterns.proxy;

/**
 * ����ģʽ��Ϊ���������ṩһ�ִ����Կ��ƶ��������ķ��ʡ�
 * 
 * ʹ�ó��ϣ�
 * 
 * Զ�̴���һ�������ڲ�ͬ�ĵ�ַ�ռ��ṩ�ֲ�����������������һ����������ڲ�ͬ��ַ�ռ����ʵ��
 * 
 * ������������������ܴ�ͨ������ģʽ�����ʵ�����ܳ�ʱ�����ʵ����
 * 
 * ��ȫ�������Կ�����ʵ����ķ���Ȩ�ޡ�
 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-19 ����10:44:26
 */
public class Zmain {

	/**
	 * @author daniel
	 * @time 2016-5-19 ����10:43:07
	 * @param args
	 */
	public static void main(String[] args) {

		
		 Proxy proxy = new Proxy(); 
		 proxy.request();
		 
/* 		Wan w=new Wan();
		WanInvocationImpl impl = new WanInvocationImpl(w);
		Wan wan = (Wan)Proxy.newProxyInstance(Wan.class.getClassLoader(),new Class[] { Wan.class }, impl);

		wan.run(); 
	
		
		List list=(List)ProxyUtil.newProxyInstance(new ArrayList(), new MyAdvice());
		  list.add("aa");
		  list.add("bb");
		  System.out.println(list.size());
		  list.clear();
		  System.out.println(list.size());
		  
		  
			
		  WanImpl list=(WanImpl)ProxyUtil.newProxyInstance(new WanImpl(), new MyAdvice());
			 
 			list.run();*/
 		  
	}

}
