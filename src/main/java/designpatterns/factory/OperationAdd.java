package designpatterns.factory;

/**
 * 
 * @author daniel ������ �̳г���
 */
class OperationAdd extends Operation {

	/*
	 * ��д�������㷽�� (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() + this.getNumberB();
		return result;
	}

}

class OperationSub extends Operation {
	/*
	 * ��д�������㷽�� (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() - this.getNumberB();
		return result;
	}

}

class OperationMul extends Operation {
	/*
	 * ��д�������㷽�� (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() * this.getNumberB();
		return result;
	}

}

class OperationDiv extends Operation {
	/*
	 * ��д�������㷽�� (non-Javadoc)
	 * 
	 * @see ding.study.designpatterns.factory.Operation#getResult()
	 */
	public double getResult() {
		double result = 0;
		result = this.getNumberA() / this.getNumberB();
		return result;
	}

}
