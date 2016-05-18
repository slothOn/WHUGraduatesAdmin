package entity;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

import junit.framework.Assert;
import service.UserDAO;
import service.impl.UserDAOImpl;

public class TestUser {
//	@Test
//	public void testSchemaExport(){
//		Configuration config = new Configuration().configure();
//		//生成表结构
//		SchemaExport export = new SchemaExport(config);
//		export.create(true,true);		
//	}
	@Test
	public void testLogin(){
		User user = new User(1,"admin","admin");
		UserDAO udao = new UserDAOImpl();
		Assert.assertEquals(true, udao.userLogin(user));
	}
}
