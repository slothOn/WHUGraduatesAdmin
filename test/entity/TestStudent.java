package entity;

import java.util.List;

import org.junit.Test;

import service.StudentDAO;
import service.impl.StudentDAOImpl;

public class TestStudent {
	/*
	@Test
	public void testStuNums(){
		StudentDAO sdao = new StudentDAOImpl();
		System.out.println(sdao.getRowsnum());
	}
	*/
	
//	@Test
//	public void OutputTest(){
//		StudentDAO sdao = new StudentDAOImpl();
//		List<Student_info> list = sdao.queryAllStudents();
//		sdao.exportStu2Excel(list, "/Users/zxc/", "test.xls");
//	}
	
	@Test
	public void testFilter(){
		StudentDAO sdao = new StudentDAOImpl();
		List<Student_info> list = sdao.queryFilterJob("", "", "北京大学", "", "", 1, false);
		System.out.println(list.size());
//		StudentDAO sdao = new StudentDAOImpl();
//		List<Student_info> list = sdao.queryFilterSchool("三好学生", "", "", "", 1, false);
//		System.out.println(list.size());
	}
}
