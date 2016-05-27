package service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import db.MyHibernateSessionFactory;
import entity.Student_info;
import entity.Student_job;
import entity.Student_school;
import service.StudentDAO;
import org.hibernate.criterion.Property;

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
			tx.rollback();
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
			tx.rollback();
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
			tx.rollback();
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
			tx.rollback();
			return false;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public List<Student_info> queryRecordsByPage(int page, boolean flag) {
		// TODO Auto-generated method stub
		int pagesize = 12;
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from Student_info";
			Query query = session.createQuery(hql);
			//不下载
			if(!flag){
				query.setFirstResult((page - 1) * pagesize);
				query.setMaxResults(pagesize);
			}
			List<Student_info> list = query.list();
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
			tx.rollback();
			return 0;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public List<Student_info> queryFilter(String sid, String sname, String gender, String political, String sprov,
			String scity, String tel, String sqq, int page, boolean flag) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 12;
		try {
			Criteria criteria = session.createCriteria(Student_info.class);
			if(sid != null && !"".equals(sid)) criteria.add(Restrictions.like("sid", "%" + sid + "%"));
			if(sname != null && !"".equals(sname)) criteria.add(Restrictions.like("sname", "%" + sname + "%"));
			if(gender != null && !"".equals(gender)) criteria.add(Restrictions.like("gender", "%" + gender + "%"));
			if(political != null && !"".equals(political)) criteria.add(Restrictions.like("political", "%" + political + "%"));
			if(sprov != null && !"".equals(sprov)) criteria.add(Restrictions.like("sprov", "%" + sprov + "%"));
			if(scity != null && !"".equals(scity)) criteria.add(Restrictions.like("scity", "%" + scity + "%"));
			if(tel != null && !"".equals(tel)) criteria.add(Restrictions.like("tel", "%" + tel + "%"));
			if(sqq != null && !"".equals(sqq)) criteria.add(Restrictions.like("sqq", "%" + sqq + "%"));
			//不下载
			if(!flag){
				criteria.setFirstResult((page - 1) * pagesize);
				criteria.setMaxResults(pagesize);
			}
			List<Student_info> list = criteria.list();
			tx.commit();
			return list;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return new ArrayList<Student_info>();
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public List<Student_info> queryFilterSchool(String activity, String honor, String startyear, String endyear,
			int page, boolean flag) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 12;
		try {
			List<Student_info> list = null;
			Criteria criteria = session.createCriteria(Student_info.class,"stu");
			DetachedCriteria detachcriteria = DetachedCriteria.forClass(Student_school.class, "school");
			if(activity != null && !"".equals(activity)) detachcriteria.add(Restrictions.like("activity", "%" + activity + "%"));
			if(honor != null && !"".equals(honor)) detachcriteria.add(Restrictions.like("honor", "%" + honor + "%"));
			if(startyear != null && !"".equals(startyear)) detachcriteria.add(Restrictions.like("startyear", "%" + startyear + "%"));
			if(endyear != null && !"".equals(endyear)) detachcriteria.add(Restrictions.like("endyear", "%" + endyear + "%"));
			detachcriteria.add(Property.forName("stu.sid").eqProperty("school.sid"));
			criteria.add(Subqueries.exists(detachcriteria.setProjection(Projections.property("school.sid"))));
			//不下载
			if(!flag){
				criteria.setFirstResult((page - 1) * pagesize);
				criteria.setMaxResults(pagesize);
			}
			list = criteria.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return new ArrayList<Student_info>();
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public List<Student_info> queryFilterJob(String time, String type, String cname, String job, String comment,
			int page, boolean flag) {
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 12;
		try {
			List<Student_info> list = null;
			Criteria criteria = session.createCriteria(Student_info.class,"stu");
			DetachedCriteria detachcriteria = DetachedCriteria.forClass(Student_job.class, "job");
			if(time != null && !"".equals(time)) detachcriteria.add(Restrictions.like("time", "%" + time + "%"));
			if(type != null && !"".equals(type)) detachcriteria.add(Restrictions.like("type", "%" + type + "%"));
			if(cname != null && !"".equals(cname)) detachcriteria.add(Restrictions.like("cname", "%" + cname + "%"));
			if(job != null && !"".equals(job)) detachcriteria.add(Restrictions.like("job", "%" + job + "%"));
			if(comment != null && !"".equals(comment)) detachcriteria.add(Restrictions.like("comment", "%" + comment + "%"));
			detachcriteria.add(Property.forName("stu.sid").eqProperty("job.sid"));
			criteria.add(Subqueries.exists(detachcriteria.setProjection(Projections.property("job.sid"))));
			//不下载
			if(!flag){
				criteria.setFirstResult((page - 1) * pagesize);
				criteria.setMaxResults(pagesize);
			}
			list = criteria.list();
			tx.commit();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return new ArrayList<Student_info>();
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public int queryFilter(String sid, String sname, String gender, String political, String sprov, String scity,
			String tel, String sqq) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Student_info.class);
			if(sid != null && !"".equals(sid)) criteria.add(Restrictions.like("sid", "%" + sid + "%"));
			if(sname != null && !"".equals(sname)) criteria.add(Restrictions.like("sname", "%" + sname + "%"));
			if(gender != null && !"".equals(gender)) criteria.add(Restrictions.like("gender", "%" + gender + "%"));
			if(political != null && !"".equals(political)) criteria.add(Restrictions.like("political", "%" + political + "%"));
			if(sprov != null && !"".equals(sprov)) criteria.add(Restrictions.like("sprov", "%" + sprov + "%"));
			if(scity != null && !"".equals(scity)) criteria.add(Restrictions.like("scity", "%" + scity + "%"));
			if(tel != null && !"".equals(tel)) criteria.add(Restrictions.like("tel", "%" + tel + "%"));
			if(sqq != null && !"".equals(sqq)) criteria.add(Restrictions.like("sqq", "%" + sqq + "%"));
			
			int pagesize = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			tx.commit();
			return pagesize;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return 0;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public int queryFilterSchool(String activity, String honor, String startyear, String endyear) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Student_info.class,"stu");
			DetachedCriteria detachcriteria = DetachedCriteria.forClass(Student_school.class, "school");
			if(activity != null && !"".equals(activity)) detachcriteria.add(Restrictions.like("activity", "%" + activity + "%"));
			if(honor != null && !"".equals(honor)) detachcriteria.add(Restrictions.like("honor", "%" + honor + "%"));
			if(startyear != null && !"".equals(startyear)) detachcriteria.add(Restrictions.like("startyear", "%" + startyear + "%"));
			if(endyear != null && !"".equals(endyear)) detachcriteria.add(Restrictions.like("endyear", "%" + endyear + "%"));
			detachcriteria.add(Property.forName("stu.sid").eqProperty("school.sid"));
			criteria.add(Subqueries.exists(detachcriteria.setProjection(Projections.property("school.sid"))));
			
			int pagesize = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			tx.commit();
			return pagesize;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return 0;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public int queryFilterJob(String time, String type, String cname, String job, String comment) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Student_info.class,"stu");
			DetachedCriteria detachcriteria = DetachedCriteria.forClass(Student_job.class, "job");
			if(time != null && !"".equals(time)) detachcriteria.add(Restrictions.like("time", "%" + time + "%"));
			if(type != null && !"".equals(type)) detachcriteria.add(Restrictions.like("type", "%" + type + "%"));
			if(cname != null && !"".equals(cname)) detachcriteria.add(Restrictions.like("cname", "%" + cname + "%"));
			if(job != null && !"".equals(job)) detachcriteria.add(Restrictions.like("job", "%" + job + "%"));
			if(comment != null && !"".equals(comment)) detachcriteria.add(Restrictions.like("comment", "%" + comment + "%"));
			detachcriteria.add(Property.forName("stu.sid").eqProperty("job.sid"));
			criteria.add(Subqueries.exists(detachcriteria.setProjection(Projections.property("job.sid"))));
			
			int pagesize = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			tx.commit();
			return pagesize;		
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
