package net.codejava.spring.dao;

import java.util.List;

import net.codejava.spring.model.DoctorWhereAbouts;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public List<DoctorWhereAbouts> getDocWhereAboutslist() {
		String hql = "FROM DoctorWhereAbouts";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<DoctorWhereAbouts> listUser = (List<DoctorWhereAbouts>) query.list();
	/*	@SuppressWarnings("unchecked")
		List<DoctorWhereAbouts> listUser = (List<DoctorWhereAbouts>) sessionFactory.getCurrentSession()
				.createCriteria(DoctorWhereAbouts.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();*/

		return listUser;
	}

	@Override
	public void updateDocWhereAbouts(DoctorWhereAbouts whereAbouts) {
		
		if(whereAbouts.getDate() != null && !whereAbouts.getDate().isEmpty()){
			sessionFactory.getCurrentSession().save(whereAbouts);
		}
	}

}
