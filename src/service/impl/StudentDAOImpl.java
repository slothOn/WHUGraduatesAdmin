package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import db.MyHibernateSessionFactory;
import entity.Student_info;
import service.StudentDAO;

public class StudentDAOImpl implements StudentDAO{

	@Override
	public List<Student_info> queryAllStudents() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from Student_info";
			Query query = session.createQuery(hql);
			List<Student_info> list = query.list();
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
	public Student_info queryStudentBySid(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Student_info stu = (Student_info) session.get(Student_info.class, sid);
			tx.commit();
			return stu;
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
	public boolean addStudent(Student_info s) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			session.save(s);
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

	@Override
	public boolean updateStudent(Student_info s) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			session.update(s);
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

	@Override
	public boolean deleteStudent(String sid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Student_info stu = (Student_info) session.get(Student_info.class, sid);
			session.delete(stu);
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

	@Override
	public List<Student_info> queryRecordsByPage(int page) {
		// TODO Auto-generated method stub
		int pagesize = 10;
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from Student_info";
			Query query = session.createQuery(hql);
			query.setFirstResult((page - 1) * pagesize);
			query.setMaxResults(pagesize);
			List<Student_info> list = query.list();
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
	public int getRowsnum() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "select count(sid) from Student_info";
			Query query = session.createQuery(hql);
			int rownum = ((Long)query.uniqueResult()).intValue();
			tx.commit();
			return rownum;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return 0;
		}finally{
			if(tx != null) tx = null;
		}
	}

	@Override
	public List<Student_info> queryFilter(String sid, String sname, String gender, String political, String sprov,
			String scity, String tel, String sqq, int page) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 10;
		try {
			Criteria criteria = session.createCriteria(Student_info.class);
			if(sid != null && !"".equals(sid)) criteria.add(Restrictions.eq("sid", sid));
			if(sname != null && !"".equals(sname)) criteria.add(Restrictions.eq("sname", sname));
			if(gender != null && !"".equals(gender)) criteria.add(Restrictions.eq("gender", gender));
			if(political != null && !"".equals(political)) criteria.add(Restrictions.eq("political", political));
			if(sprov != null && !"".equals(sprov)) criteria.add(Restrictions.eq("sprov", sprov));
			if(scity != null && !"".equals(scity)) criteria.add(Restrictions.eq("scity", scity));
			if(tel != null && !"".equals(tel)) criteria.add(Restrictions.eq("tel", tel));
			if(sqq != null && !"".equals(sqq)) criteria.add(Restrictions.eq("sqq", sqq));
			criteria.setFirstResult((page - 1) * pagesize);
			criteria.setMaxResults(pagesize);
			List<Student_info> list = criteria.list();
			tx.commit();
			return list;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return new ArrayList<Student_info>();
		}finally{
			if(tx != null) tx = null;
		}
	}

	@Override
	public List<Student_info> queryFilterSchool(String activity, String honor, String startyear, String endyear,
			int page) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 10;
		try {
			List<Student_info> list = null;
			String hql = "select distinct stu from Student_info stu inner join stu.sid = ";
			
			tx.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.commit();
			return new ArrayList<Student_info>();
		}finally{
			if(tx != null) tx = null;
		}
	}
	
}
