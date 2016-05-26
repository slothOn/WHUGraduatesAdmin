package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import db.MyHibernateSessionFactory;
import entity.Student_job;
import entity.Student_school;
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
			tx.rollback();
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
			tx.rollback();
			return null;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public Integer addRecord(Student_job s) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try{
			session.save(s);
			tx.commit();
			Integer sjid = s.getSjid();
			return sjid;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return 0;
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
			tx.rollback();
			return false;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public List<Student_job> queryFilter(String time, String type, String cname, String job, String comment, int page,
			boolean flag) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 12;
		try {
			Criteria criteria = session.createCriteria(Student_job.class);
			if(time != null && !"".equals(time)) criteria.add(Restrictions.like("time", "%" + time + "%"));
			if(type != null && !"".equals(type)) criteria.add(Restrictions.like("type", "%" + type + "%"));
			if(cname != null && !"".equals(cname)) criteria.add(Restrictions.like("cname", "%" + cname + "%"));
			if(job != null && !"".equals(job)) criteria.add(Restrictions.like("job", "%" + job + "%"));
			if(comment != null && !"".equals(comment)) criteria.add(Restrictions.like("comment", "%" + comment + "%"));
			//不下载
			if(!flag){
				criteria.setFirstResult((page - 1) * pagesize);
				criteria.setMaxResults(pagesize);
			}
			List<Student_job> list = criteria.list();
			tx.commit();
			return list;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return new ArrayList<Student_job>();
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public int queryFilter(String time, String type, String cname, String job, String comment) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Student_job.class);
			if(time != null && !"".equals(time)) criteria.add(Restrictions.like("time", "%" + time + "%"));
			if(type != null && !"".equals(type)) criteria.add(Restrictions.like("type", "%" + type + "%"));
			if(cname != null && !"".equals(cname)) criteria.add(Restrictions.like("cname", "%" + cname + "%"));
			if(job != null && !"".equals(job)) criteria.add(Restrictions.like("job", "%" + job + "%"));
			if(comment != null && !"".equals(comment)) criteria.add(Restrictions.like("comment", "%" + comment + "%"));
			
			int num = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			tx.commit();
			return num;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return 0;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

}
