package designpatterns.decorator;

public class TShirts extends Finery {

	@Override
	/**
	 * 重写父类show方法
	 */
	public void show() {
		System.out.println("穿T恤");
		super.show();
	}

}
