package action;

import java.util.List;

import entity.Com_info;
import service.ComDAO;
import service.impl.ComDAOImpl;

public class ComAction extends SuperAction{
	public String comquery(){
		ComDAO cdao = new ComDAOImpl();
		List<Com_info> list = cdao.queryAllComs();
		request.setAttribute("coms_list", list);
		return "query_success";
	}
	
	public String modify(){
		String cid = request.getParameter("cid");
		ComDAO cdao = new ComDAOImpl();
		Com_info com = cdao.queryComBySid(cid);
		request.setAttribute("com_info", com);
		return "modify_success";
	}
	
	public String delete(){
		String cid = request.getParameter("cid");
		ComDAO cdao = new ComDAOImpl();
		cdao.deleteCom(cid);
		return "delete_success";
	}
	
	public String update(){
		return "";
	}
}
