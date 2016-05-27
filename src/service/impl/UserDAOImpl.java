package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.User;
import service.UserDAO;
import util.MD5Util;

public class UserDAOImpl implements UserDAO{

	@Override
	public boolean userLogin(User u) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from User where username=? and password=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, u.getUsername());
			query.setParameter(1, MD5Util.MD5Encode(u.getPassword()));
			List list = query.list();
			tx.commit();
			if(list != null && list.size() > 0) return true;
			else return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return false;
		}finally{
			if(tx != null) tx = null;
		}
	}

	@Override
	public void changepwd(String username, String newpwd) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "update User user set user.password=? where username=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, MD5Util.MD5Encode(newpwd));
			query.setParameter(1, username);
			//query.setParameter(1, MD5Util.MD5Encode(u.getPassword()));
			int result = query.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
		}finally{
			if(tx != null) tx = null;
		}
	}


}
