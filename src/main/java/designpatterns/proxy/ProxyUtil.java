package designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ���ɴ���������
 * 
 * @author hpo
 * 
 */
public class ProxyUtil {
	/**
	 * ��ö���Ĵ���
	 * 
	 * @param target
	 *            ��Ҫ������Ķ���
	 * @param advice
	 *            ���������ִ�з���������ӿ�
	 * @return
	 */
	public static Object newProxyInstance(final Object target,
										  final Advice advice) {
		Object returnObj = (Object) Proxy.newProxyInstance(target.getClass()
				.getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method,
										 Object[] args) throws Throwable {
						advice.beforMethod(method);
						Object relObj = method.invoke(target, args);
						advice.afterMethod(method);
						return relObj;
					}
				});
		return returnObj;
	}
}