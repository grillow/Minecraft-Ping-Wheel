package nx.pingwheel.common.networking;

import java.util.HashMap;

import nx.pingwheel.common.core.ClientCore;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class SideChannelNetworkHandler {
	private static SideChannelNetworkHandler instance;

	public static synchronized SideChannelNetworkHandler getInstance() {
		if (instance == null) {
			instance = new SideChannelNetworkHandler();
		}
		return instance;
	}

	public SideChannelNetworkHandler() {

	}

	private static Gson gson = new Gson();
	private Connection connection = null;
	private Channel channel = null;
	private String queue = null;
	private String tag = null;
	private static final String EXCHANGE_NAME = "PingLocation";

	// TODO: handle
	public void connect(String uri) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setUri(uri);

			connection = factory.newConnection();

			channel = connection.createChannel();
			channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, false, true,
					new HashMap<String, Object>());

			queue = channel.queueDeclare().getQueue();
			channel.queueBind(queue, EXCHANGE_NAME, "");

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) {
					String receivedMessage;
					try {
						receivedMessage = new String(body, "UTF-8");
						PingLocationPacket packet = gson.fromJson(receivedMessage, PingLocationPacket.class);
						ClientCore.onPingLocation(packet);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			tag = channel.basicConsume(queue, true, consumer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO: handle
	// TODO: this doesn't work
	public void close() {
		try {
			channel.basicCancel(tag);

			channel.queueUnbind(queue, EXCHANGE_NAME, "");
			channel.queueDelete(queue);

			channel.close();

			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO: handle
	public void send(PingLocationPacket packet) {
		try {
			channel.basicPublish(EXCHANGE_NAME, "", null, gson.toJson(packet).getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
