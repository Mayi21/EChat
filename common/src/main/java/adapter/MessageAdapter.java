package adapter;


import entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageAdapter.class);

	public static void sendMessage(Message message) {
		LOGGER.info("start send message");



	}


}
