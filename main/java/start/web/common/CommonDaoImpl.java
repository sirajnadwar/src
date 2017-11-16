package start.web.common;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonDaoImpl implements CommonDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public void saveOrUpdateCommonEntity(CommonEntity entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}
}
