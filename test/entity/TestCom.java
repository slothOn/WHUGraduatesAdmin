package entity;

import java.util.Date;

import org.junit.Test;

import service.ComDAO;
import service.impl.ComDAOImpl;


public class TestCom {
	@Test
	public void testCom(){
		Com_info com_info = new Com_info(0, "Google", "CS", "p", "US", "CA", new Date(), new Date());
		ComDAO cdao = new ComDAOImpl();
		cdao.addCom(com_info);
	}
}
