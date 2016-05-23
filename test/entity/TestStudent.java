package entity;

import org.junit.Test;

import service.StudentDAO;
import service.impl.StudentDAOImpl;

public class TestStudent {
	@Test
	public void testStuNums(){
		StudentDAO sdao = new StudentDAOImpl();
		System.out.println(sdao.getRowsnum());
	}
}
