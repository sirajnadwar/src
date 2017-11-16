package start.web.service.registration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import start.web.common.Events;
import start.web.model.registration.User;

public class EventPublisherService {
	@Autowired
	AmqpTemplate template;
    public void publishEvent(Events event, User user) throws IOException, TimeoutException {
        Map<String, Object> message = new HashMap<String, Object>();
        message.put("event", event);
        message.put("username", user.getFirstname());
        message.put("email", user.getEmail());
        template.convertAndSend("emailAccountExchange","email.Account",message);
    }
}
