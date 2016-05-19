package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Com_hire;
import service.HireDAO;

public class HireDAOImpl implements HireDAO{

	@Override
	public List<Com_hire> queryAllRecords() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from Com_hire";
			Query query = session.createQuery(hql);
			List<Com_hire> list = query.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return null;
		}finally{
			if(tx != null) tx = null;
		}
	}

	@Override
	public Com_hire queryHireBySid(String sid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addHire(Com_hire s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateHire(Com_hire s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteHire(String hid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Com_hire ch = (Com_hire) session.get(Com_hire.class, hid);
			session.delete(ch);
			tx.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx != null) tx = null;
		}
	}

}
