package designpatterns.decorator;

public class TShirts extends Finery {

	@Override
	/**
	 * ��д����show����
	 */
	public void show() {
		System.out.println("��T��");
		super.show();
	}

}
