
package designpatterns.strategy;
/**
 * PS:����ģʽ��һ�ֶ���һϵ���㷨�ķ������Ӹ�����������������Щ�㷨��ɵĶ�����ͬ�Ĺ�����ֻ��ʵ�ֲ�ͬ������������ͬ�ķ�ʽ�������е��㷨�����ٸ����㷨����ʹ���㷨��֮�����ϡ�

 

����ģʽ��Strategy����ΪContext������һϵ�еĿɹ����õ��㷨����Ϊ���̳���������ȡ��Щ�㷨�еĹ������ܡ�

 

���˵�Ԫ���ԣ�ÿ�������ͨ���Լ��Ľӿڲ��ԡ�

 

-------------------------------------------------------------------------------------------
 

 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-17 ����10:14:59
 */
public class TestMain {

 /**
  * @param args
  */
 public static void main(String[] args) {
   Context context;
  context= new Context(new ConcreteStrategyA());
  context.ContextInterface();
  
  context= new Context(new ConcreteStrategyB());
  context.ContextInterface();
  
  context= new Context(new ConcreteStrategyC());
  context.ContextInterface();

 }

}