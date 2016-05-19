package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import entity.Com_hire;
import entity.Com_info;
import service.ComDAO;
import service.HireDAO;
import service.impl.ComDAOImpl;
import service.impl.HireDAOImpl;

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
	
	public String add() throws Exception{
		Com_info com = new Com_info();
		com.setCid(0);
		com.setCname(request.getParameter("cname"));
		com.setCfield(request.getParameter("cfield"));
		com.setCprov(request.getParameter("cprov"));
		com.setCcity(request.getParameter("ccity"));
		com.setCtype(request.getParameter("ctype"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		com.setStartdate(sdf.parse(request.getParameter("startdate")));
		com.setEnddate(sdf.parse(request.getParameter("enddate")));
		ComDAO cdao = new ComDAOImpl();
		if(cdao.addCom(com)){
			return "add_success";
		}else return "add_error";
	}
	
	public String hirequery(){
		HireDAO hdao = new HireDAOImpl();
		List<Com_hire> list = hdao.queryAllRecords();
		request.setAttribute("hires_list", list);
		return "query_success";
	}
	
	public String hiredelete(){
		String hid = request.getParameter("hid");
		HireDAO hdao = new HireDAOImpl();
		hdao.deleteHire(hid);
		return "delete_success";
	}
}
