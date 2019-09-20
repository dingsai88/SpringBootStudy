package designpatterns.interpreter;

/**
 * ���ն˽����� ����һ��ʵ����AbstractExpression
 * �ӿڵ��࣬���������﷨���з�ĩ�˽ڵ���﷨����������һ��AbstractExpression(s)�����ã�������ÿ���ӽڵ��Interpret������
 * 
 * @author daniel
 * 
 */
public class NonterminalExpression extends AbstractExpression {

	@Override
	public void interpret(Context context) {
 		System.out.println("���ն˽�����");
	}

}