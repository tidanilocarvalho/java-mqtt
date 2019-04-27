package runner;

import com.sample.mqtt.publish.Publisher;

public class MainPublisher {

	public static void main(String[] args) {
		try {
			new Publisher().pub();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
