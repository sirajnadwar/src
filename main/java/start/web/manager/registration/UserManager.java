package start.web.manager.registration;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import start.web.common.CommonDao;
import start.web.dao.registration.UserDao;
import start.web.model.registration.User;

@Transactional
public class UserManager {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CommonDao commonDao;
	
	public void saveUser(User user) {
		userDao.register(user);
		user.setCommonFields(new Date());
		commonDao.saveOrUpdateCommonEntity(user);
	}
	
	public List<User> listUsers(String username) {
		return userDao.getUsers(username);
	}
}
