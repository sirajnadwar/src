package start.web.service.registration;

import start.web.model.registration.Login;
import start.web.model.registration.User;

public interface UserService {
	
	void register(User user);
	
	User validateUser(Login login);

}
