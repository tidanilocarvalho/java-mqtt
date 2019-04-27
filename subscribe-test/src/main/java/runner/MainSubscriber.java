package runner;

import com.sample.mqtt.subscribe.Subscriber;

public class MainSubscriber {

	public static void main(String[] args) {
		try {
			new Subscriber().sub();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
