package start.web.service.registration;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;

import start.web.common.Events;
import start.web.manager.registration.UserManager;
import start.web.model.registration.Login;
import start.web.model.registration.User;

public class UserServiceImpl implements UserService {
	@Autowired
	private UserManager userManager;
	@Autowired
    EventPublisherService eventPublisherService;

	public void register(User user) {
		List<User> listUsers = userManager.listUsers(user.getEmail());
		if (listUsers.size() == 0) {
			userManager.saveUser(user);
		}
        try {
			eventPublisherService.publishEvent(Events.EmailAccountSuccessAck, user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

}
