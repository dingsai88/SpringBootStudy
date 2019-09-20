package designpatterns.proxy;

import java.lang.reflect.Method;

/**
 * 
 * ����ӿ�
 * ����������淽����
 * �ڴ�����ִ��ǰ��ִ�к�ִ�е���
 * advice����
 * @author hpo
 *
 */
public interface Advice {
/**
 * �ڴ�����ִ��ǰִ�еķ���
 * @param method
 */
 public void beforMethod(Method method);
 /**
  * �ڴ�����ִ�к�ִ�еķ���
  * @param method
  */
 public void afterMethod(Method method);
}

 