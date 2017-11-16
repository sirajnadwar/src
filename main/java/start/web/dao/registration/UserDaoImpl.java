package start.web.dao.registration;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import start.web.model.registration.Login;
import start.web.model.registration.User;

public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void register(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	public List<User> getUsers(String username) {
		Session session = sessionFactory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(builder.equal(root.get("email"), username));
		Query<User> q = session.createQuery(query);
		List<User> list = q.getResultList();
		return list;
	}

	public User validateUser(Login login) {
		// TODO Auto-generated method stub
		return null;
	}

}
