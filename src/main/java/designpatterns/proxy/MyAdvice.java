package designpatterns.proxy;

import java.lang.reflect.Method;
/*
 * ����ӿڵ�ʵ����
 */
public class MyAdvice implements Advice { 
 public void beforMethod(Method method) {
  System.out.println("������ʼ:����ִ�еķ�����:"+method.getName());
 }

 public void afterMethod(Method method) {
  System.out.println("��������:����ִ�еķ�����:"+method.getName());
 }

}