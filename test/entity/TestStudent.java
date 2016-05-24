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
	
	@Test
	public void OutputTest(){
		StudentDAO sdao = new StudentDAOImpl();
		List<Student_info> list = sdao.queryAllStudents();
		sdao.exportStu2Excel(list, "/Users/zxc/", "test.xls");
	}
}
