package designpatterns.interpreter;
/**
 * ������ģʽ������
 * - ����һ�������Interpret�����������﷨�������еĽڵ㶼����ʵ�ָó��󷽷���
 * @author daniel
 *
 */
abstract class AbstractExpression {
//���ͷ���
 public abstract void interpret(Context context);
 
}