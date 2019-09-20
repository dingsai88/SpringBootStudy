package designpatterns.interpreter;

/**
 * ������֮���һЩȫ����Ϣ
 * Interpreter��������Ҫ����Ϣ������������Ϣ��Interpreter����ȫ�ֿɼ����䵱����AbstractExpresssion
 * ʵ��֮���ͨѶƵ����
 * 
 * @author daniel
 * 
 */
public class Context {
	private String input;
	private String output;

	/**
	 * @return the output
	 */
	public String getOutput() {
		return output;
	}

	/**
	 * @param output
	 *            the output to set
	 */
	public void setOutput(String output) {
		this.output = output;
	}

	/**
	 * @return the input
	 */
	public String getInput() {
		return input;
	}

	/**
	 * @param input
	 *            the input to set
	 */
	public void setInput(String input) {
		this.input = input;
	}

}