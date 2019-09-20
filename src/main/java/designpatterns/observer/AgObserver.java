package designpatterns.observer;

import java.util.Observable;
import java.util.Observer;

public class AgObserver implements Observer {
	private String name;

	public AgObserver(String name) {
		this.name = name;
	}

	public void update(Observable arg0, Object arg1) {
		if (arg1 instanceof Double) {
			System.out.println(this.name + "�۲쵽�ļ۸����Ϊ\n" + ((Double) arg1).toString());
		}

	}

}