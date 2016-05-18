package service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import db.MyHibernateSessionFactory;
import entity.Student_school;
import service.StudentschoolDAO;

public class StudentschoolDAOImpl implements StudentschoolDAO{

	@Override
	public List<Student_school> queryAllRecords() {
		// TODO Auto-generated method stub
		List<Student_school> list = null;
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from Student_school";
			Query query = session.createQuery(hql);
			list = query.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return list;
		}finally {
			if(tx != null) tx = null;
		}
	}

	@Override
	public List<Student_school> queryRecordBySid(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		List<Student_school> list = null;
		try {
			String hql = "from Student_school where sid=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, sid);
			list = (List<Student_school>)query.list();
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
	public boolean addRecord(Student_school s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRecord(Student_school s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRecord(String sid) {
		// TODO Auto-generated method stub
		return false;
	}

}
