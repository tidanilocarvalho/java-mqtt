package com.sample.mqtt.subscribe;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Subscriber implements MqttCallback {

	private String clientId = MqttClient.generateClientId();
	private String serverUri = "tcp://test.mosquitto.org:1883";
	private int qos = 0;
	private int connectionTimeout = 120000;
	private int keepAliveInterval = 30;
	
	private String topic = "univas/flisol/chat/danilo";

	public void sub() throws MqttException {
		MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
		//mqttConnectOptions.setUserName("<userhere>");
		//mqttConnectOptions.setPassword("<passwordhere>".toCharArray());
		mqttConnectOptions.setConnectionTimeout(connectionTimeout);
		mqttConnectOptions.setKeepAliveInterval(keepAliveInterval);
		mqttConnectOptions.setCleanSession(true);
		
		MqttAsyncClient mqttAsyncClient = new MqttAsyncClient(serverUri, clientId);
		mqttAsyncClient.setCallback(this);

		IMqttToken connect = mqttAsyncClient.connect(mqttConnectOptions);
		connect.waitForCompletion(connectionTimeout);

		mqttAsyncClient.subscribe(topic, qos);
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws Exception {
		System.out.println(String.format("Message arrived topic %s message %s QoS %d", 
			topic, 
			new String(message.getPayload()), 
			message.getQos()));
	}

	@Override
	public void connectionLost(Throwable cause) {
		System.out.println(String.format("Connection lost %s", cause.getMessage()));
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println(String.format("Delivery complete %s", token.getMessageId()));
	}
	
}
