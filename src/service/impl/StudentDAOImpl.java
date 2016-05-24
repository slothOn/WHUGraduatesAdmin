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
			Criteria criteria = session.createCriteria(Student_info.class,"stu");
			DetachedCriteria detachcriteria = DetachedCriteria.forClass(Student_school.class, "school");
			if(activity != null && !"".equals(activity)) detachcriteria.add(Restrictions.eq("activity", activity));
			if(honor != null && !"".equals(honor)) detachcriteria.add(Restrictions.eq("honor", honor));
			if(startyear != null && !"".equals(startyear)) detachcriteria.add(Restrictions.eq("startyear", startyear));
			if(endyear != null && !"".equals(endyear)) detachcriteria.add(Restrictions.eq("endyear", endyear));
			detachcriteria.add(Property.forName("stu.sid").eqProperty("school.sid"));
			criteria.add(Subqueries.exists(detachcriteria.setProjection(Projections.property("school.sid"))));
			list = criteria.list();
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
	public List<Student_info> queryFilterJob(String time, String type, String cname, String job, String comment,
			int page) {
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 10;
		try {
			List<Student_info> list = null;
			Criteria criteria = session.createCriteria(Student_info.class,"stu");
			DetachedCriteria detachcriteria = DetachedCriteria.forClass(Student_job.class, "job");
			if(time != null && !"".equals(time)) detachcriteria.add(Restrictions.eq("time", time));
			if(type != null && !"".equals(type)) detachcriteria.add(Restrictions.eq("type", type));
			if(cname != null && !"".equals(cname)) detachcriteria.add(Restrictions.eq("cname", cname));
			if(job != null && !"".equals(job)) detachcriteria.add(Restrictions.eq("job", job));
			if(comment != null && !"".equals(comment)) detachcriteria.add(Restrictions.eq("comment", comment));
			
			detachcriteria.add(Property.forName("stu.sid").eqProperty("job.sid"));
			criteria.add(Subqueries.exists(detachcriteria.setProjection(Projections.property("job.sid"))));
			list = criteria.list();
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
	public void exportStu2Excel(List<Student_info> list, String filepath, String filename){
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(filename);
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("学号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("政治面貌");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("省市");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("专业");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("电话");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("QQ");
		cell.setCellStyle(style);
		
		for(int i = 1; i <= list.size(); i++){
			row = sheet.createRow(i);
			Student_info stu = list.get(i-1);
			row.createCell(0).setCellValue(stu.getSid());
			row.createCell(1).setCellValue(stu.getSname());
			row.createCell(2).setCellValue(stu.getGender());
			row.createCell(3).setCellValue(stu.getPolitical());
			row.createCell(4).setCellValue(stu.getSprov() + "" + stu.getScity());
			row.createCell(5).setCellValue(stu.getMajor());
			row.createCell(6).setCellValue(stu.getTel());
			row.createCell(7).setCellValue(stu.getSqq());
		}
		try {
			FileOutputStream out = new FileOutputStream(filepath + filename);
			wb.write(out);
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
