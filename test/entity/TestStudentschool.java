package entity;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import db.MyHibernateSessionFactory;
import service.StudentDAO;
import service.impl.StudentDAOImpl;

public class TestStudentschool {
	/*
	@Test
	public void testInsert(){
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Student_school stu = new Student_school(0, "2012301130115", "罗文鑫", "b", "c", "2013", "2013");
		session.save(stu);
		tx.commit();
	}
	*/
	
	@Test
	public void testQuery(){
		StudentDAO studao = new StudentDAOImpl();
		List<Student_info> list = studao.queryFilterSchool("a", "", "", "", 0);
		for(Student_info stu:list){
			System.out.println(stu);
		}
	}
}
