package designpatterns.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class WanInvocationImpl implements InvocationHandler {
	private Object obj;

	public WanInvocationImpl(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("��ǰ׼��");
		Object resultObj = method.invoke(obj, args);
		System.out.println("������Ϣ");
		return resultObj;
	}

}
