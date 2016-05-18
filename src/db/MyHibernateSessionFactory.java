package db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class MyHibernateSessionFactory {
	private static SessionFactory sf;
	private MyHibernateSessionFactory() {}
	public static SessionFactory getInstance(){
		if(sf == null) {
			Configuration cfg = new Configuration().configure();
			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).build();
			sf = cfg.buildSessionFactory(serviceRegistry);
		}
		return sf;
	}
}
