package start.web.listener.registration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import start.web.common.Events;

@Component
public class EmailAccountAckListener implements MessageListener {

	@SuppressWarnings("unchecked")
	public void onMessage(Message message) {
		Map<String, Object> map = (HashMap<String, Object>) SerializationUtils.deserialize(message.getBody());
		//EmailAccountHandler emailAccountHandler = new EmailAccountHandler();
		EmailAccountHandler.handler((Events)map.get("event"), map);
	}
}
