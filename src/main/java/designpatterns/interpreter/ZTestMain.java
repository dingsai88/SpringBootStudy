package designpatterns.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * ������ģʽ��interpreter��:����һ�����ԣ����������ķ���һ�ֱ�ʾ��������һ�������������������ʹ�øñ�ʾ�����������еľ��ӡ�
 * 
 * ���:
 * �ն˽�����
���ն˽�����
�ն˽�����
�ն˽�����

 * 
 * @author daniel
 * @email 576699909@qq.com
 * @time 2016-5-31 ����2:16:37
 */
public class ZTestMain {

 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Context context = new Context();
		List<AbstractExpression> list = new ArrayList<AbstractExpression>();
		list.add(new TerminalExpression());
		list.add(new NonterminalExpression());
		list.add(new TerminalExpression());
		list.add(new TerminalExpression());
		for (AbstractExpression exp : list) {
			exp.interpret(context);
		}

	}

}