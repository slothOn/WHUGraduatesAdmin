package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Student_info;
import entity.Student_job;
import service.StudentjobDAO;

public class StudentjobDAOImpl implements StudentjobDAO{

	@Override
	public List<Student_job> queryAllRecords() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from Student_job";
			Query query = session.createQuery(hql);
			List<Student_job> list = query.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return null;
		}finally {
			if(tx != null) tx = null;
		}
	}

	@Override
	public List<Student_job> queryRecordBySid(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		List<Student_job> list = null;
		try {
			String hql = "from Student_job where sid=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, sid);
			list = query.list();
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
	public boolean addRecord(Student_job s) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try{
			session.save(s);
			tx.commit();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx != null) tx = null;
		}
	}

	@Override
	public boolean updateRecord(Student_job s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRecord(String sjid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try{
			Student_job stujob = (Student_job) session.get(Student_job.class, Integer.valueOf(sjid));
			session.delete(stujob);
			tx.commit();
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return false;
		}finally{
			if(tx != null) tx = null;
		}
	}

}
