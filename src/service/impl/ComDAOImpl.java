package service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import db.MyHibernateSessionFactory;
import entity.Com_info;
import service.ComDAO;

public class ComDAOImpl implements ComDAO{

	@Override
	public List<Com_info> queryAllComs() {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			String hql = "from Com_info";
			Query query = session.createQuery(hql);
			List<Com_info> list = query.list();
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
	public Com_info queryComBySid(String cid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Com_info cinfo = (Com_info) session.get(Com_info.class, Integer.valueOf(cid));
			tx.commit();
			return cinfo;
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
	public boolean addCom(Com_info s) {
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
			tx.rollback();
			return false;
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public boolean updateCom(Com_info s) {
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
	public boolean deleteCom(String cid) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Com_info com = (Com_info) session.get(Com_info.class, Integer.valueOf(cid));
			session.delete(com);
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
	public List<Com_info> queryFilter(String cname, String cfield, String ctype, String cprov, String ccity,
			String startdate, String enddate, int page, boolean flag) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		int pagesize = 12;
		try {
			Criteria criteria = session.createCriteria(Com_info.class);
			if(cname != null && !"".equals(cname)) criteria.add(Restrictions.like("cname", "%" + cname + "%"));
			if(cfield != null && !"".equals(cfield)) criteria.add(Restrictions.like("cfield", "%" + cfield + "%"));
			if(ctype != null && !"".equals(ctype)) criteria.add(Restrictions.like("ctype", "%" + ctype + "%"));
			if(cprov != null && !"".equals(cprov)) criteria.add(Restrictions.like("cprov", "%" + cprov + "%"));
			if(ccity != null && !"".equals(ccity)) criteria.add(Restrictions.like("ccity", "%" + ccity + "%"));
			if(startdate != null && !"".equals(startdate)) criteria.add(Restrictions.like("startdate", "%" + startdate + "%"));
			if(enddate != null && !"".equals(enddate)) criteria.add(Restrictions.like("enddate", "%" + enddate + "%"));
			//不下载
			if(!flag){
				criteria.setFirstResult((page - 1) * pagesize);
				criteria.setMaxResults(pagesize);
			}
			List<Com_info> list = criteria.list();
			tx.commit();
			return list;		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			return new ArrayList<Com_info>();
		}finally{
			if(tx != null) tx = null;
			
		}
	}

	@Override
	public int queryFilter(String cname, String cfield, String ctype, String cprov, String ccity, String startdate,
			String enddate) {
		// TODO Auto-generated method stub
		Transaction tx = null;
		Session session = MyHibernateSessionFactory.getInstance().getCurrentSession();
		tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(Com_info.class);
			if(cname != null && !"".equals(cname)) criteria.add(Restrictions.like("cname", "%" + cname + "%"));
			if(cfield != null && !"".equals(cfield)) criteria.add(Restrictions.like("cfield", "%" + cfield + "%"));
			if(ctype != null && !"".equals(ctype)) criteria.add(Restrictions.like("ctype", "%" + ctype + "%"));
			if(cprov != null && !"".equals(cprov)) criteria.add(Restrictions.like("cprov", "%" + cprov + "%"));
			if(ccity != null && !"".equals(ccity)) criteria.add(Restrictions.like("ccity", "%" + ccity + "%"));
			if(startdate != null && !"".equals(startdate)) criteria.add(Restrictions.like("startdate", "%" + startdate + "%"));
			if(enddate != null && !"".equals(enddate)) criteria.add(Restrictions.like("enddate", "%" + enddate + "%"));
			
			int num = ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			tx.commit();
			return num;		
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
