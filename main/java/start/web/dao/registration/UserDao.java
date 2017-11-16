package start.web.dao.registration;

import start.web.model.registration.User;

import java.util.List;

import start.web.model.registration.Login;

public interface UserDao {
	void register(User user);
	List<User> getUsers(String username);
	User validateUser(Login login);
}
