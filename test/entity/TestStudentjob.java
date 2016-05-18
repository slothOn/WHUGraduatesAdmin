package entity;

import java.util.List;

import org.junit.Test;

import service.StudentjobDAO;
import service.impl.StudentjobDAOImpl;

public class TestStudentjob {
	@Test
	public void testQueryAlljob(){
		StudentjobDAO sdao = new StudentjobDAOImpl();
		List<Student_job> list = sdao.queryAllRecords();
		for(Student_job sjob:list){
			System.out.println(sjob);
		}
	}
}
