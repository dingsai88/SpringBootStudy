package designpatterns.interpreter;
/**
 * �ն˽�����
 * - ʵ�ֺ��﷨��ĩ�˷�����ص�Interpret������
- ��ÿ�����ӵ�ĩ�˷����о���Ҫһ��TerminalExpressionʵ����
 * @author daniel
 *
 */
public class TerminalExpression extends AbstractExpression {

 @Override
 public void interpret(Context context) {
   System.out.println("�ն˽�����");
  
 }

}