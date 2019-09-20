package designpatterns.strategy;

/**
 * 
 * @author daniel ά��Strategy���������
 */
class Context {
	Strategy strategy;

	/**
	 * ��ʼ��������
	 * 
	 * @param strategy
	 */
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * �����㷨 ʵ�ַ���
	 */
	public void ContextInterface() {
		strategy.AlgorithmInterface();
	}

}
