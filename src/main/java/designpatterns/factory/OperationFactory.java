package designpatterns.factory;

/**
 * ������
 * 
 * @author daniel
 * 
 */
public class OperationFactory {

	/**
	 * ��������
	 * 
	 * @param operate
	 * @return
	 */
	public static Operation createOperate(char operate) {
		Operation oper = null;
		switch (operate) {
		case '+':
			oper = new OperationAdd();
			break;
		case '-':
			oper = new OperationSub();
			break;
		case '*':
			oper = new OperationMul();
			break;
		case '/':
			oper = new OperationDiv();
			break;
		}
		return oper;
	}

}