package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import db.MyHibernateSessionFactory;
import entity.Student_info;
import entity.Student_job;
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
	public Integer addRecord(Student_school s) {
		// TODO Auto-generated method stub
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(s);
			tx.commit();
			return s.getSsid();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return 0;
		}finally {
			if(tx != null) tx = null;
		}
	}

	@Override
	public boolean updateRecord(Student_school s) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRecord(String ssid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try{
			Student_school stusch = (Student_school) session.get(Student_school.class, Integer.valueOf(ssid));
			session.delete(stusch);
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
	public List<Student_school> queryFilter(String activity, String honor, String startyear, String endyear, int page,
			boolean flag) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 10;
		try {
			Criteria criteria = session.createCriteria(Student_school.class);
			if(activity != null && !"".equals(activity)) criteria.add(Restrictions.like("activity", "%" + activity + "%"));
			if(honor != null && !"".equals(honor)) criteria.add(Restrictions.like("honor", "%" + honor + "%"));
			if(startyear != null && !"".equals(startyear)) criteria.add(Restrictions.like("startyear", "%" + startyear + "%"));
			if(endyear != null && !"".equals(endyear)) criteria.add(Restrictions.like("endyear", "%" + endyear + "%"));
			//不下载
			if(!flag){
				criteria.setFirstResult((page - 1) * pagesize);
				criteria.setMaxResults(pagesize);
			}
			List<Student_school> list = criteria.list();
			tx.commit();
			return list;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return new ArrayList<Student_school>();
		}finally{
			if(tx != null) tx = null;
		}
	}

	@Override
	public int queryFilter(String activity, String honor, String startyear, String endyear) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Student_school.class);
			if(activity != null && !"".equals(activity)) criteria.add(Restrictions.like("activity", "%" + activity + "%"));
			if(honor != null && !"".equals(honor)) criteria.add(Restrictions.like("honor", "%" + honor + "%"));
			if(startyear != null && !"".equals(startyear)) criteria.add(Restrictions.like("startyear", "%" + startyear + "%"));
			if(endyear != null && !"".equals(endyear)) criteria.add(Restrictions.like("endyear", "%" + endyear + "%"));			
			int num = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			tx.commit();
			return num;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return 0;
		}finally{
			if(tx != null) tx = null;
		}
	}

}
