package action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import entity.Com_hire;
import entity.Com_info;
import entity.Student_job;
import service.ComDAO;
import service.HireDAO;
import service.impl.ComDAOImpl;
import service.impl.HireDAOImpl;

public class ComAction extends SuperAction{
	
	private InputStream inputStream;
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public String comquery(){
		/*ComDAO cdao = new ComDAOImpl();
		List<Com_info> list = cdao.queryAllComs();
		request.setAttribute("coms_list", list);*/
		
		String casetype = request.getParameter("casetype");
		boolean flag = false;
		if(casetype != null && casetype.equals("1")) flag = true;
		
		String cname = request.getParameter("cname");
		String cfield = request.getParameter("cfield");
		String ctype = request.getParameter("ctype");
		String cprov = request.getParameter("cprov");
		String ccity = request.getParameter("ccity");
		String startdate = request.getParameter("startdate");
		String enddate = request.getParameter("enddate");
		
		if(cname == null || "".equals(cname)) cname = "";
		if(cfield == null || "".equals(cfield)) cfield = "";
		if(ctype == null || "".equals(ctype)) ctype = "";
		if(cprov == null || "".equals(cprov)) cprov = "";
		if(ccity == null || "".equals(ccity)) ccity = "";
		if(startdate == null || "".equals(startdate)) startdate = "";
		if(enddate == null || "".equals(enddate)) enddate = "";
		
		String pagestr = request.getParameter("page") == null || "".equals(request.getParameter("page")) ? "1": request.getParameter("page");		
		int pagenum = Integer.valueOf(pagestr);	
		String parameterurl = "cname=" + cname + "&cfield=" + cfield + "&ctype=" + ctype +"&cprov=" + cprov
				+ "&ccity=" + ccity + "&startdate=" + startdate + "&enddate=" + enddate;
		
			
		ComDAO cdao = new ComDAOImpl();
		List<Com_info> list = cdao.queryFilter(cname, cfield, ctype, cprov, ccity, startdate, enddate, pagenum, flag);
		
		if(flag){
			exportCom2Excel(list, "", "");
			return "export_success";
		}
		
		int rowsnum = cdao.queryFilter(cname, cfield, ctype, cprov, ccity, startdate, enddate);
		int pagesize = 0;
		if(rowsnum % 12 == 0){
			pagesize = rowsnum / 12;
		}else pagesize = rowsnum / 12 + 1;
		
		int beforepage = pagenum - 1;
		int afterpage = pagenum + 1;
		beforepage = beforepage > 0? beforepage : 1;
		afterpage = afterpage <= pagesize? afterpage : pagesize; 
		
		request.setAttribute("coms_list", list);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("beforepage", beforepage);
		request.setAttribute("afterpage", afterpage);
		
		request.setAttribute("methodurl", "comquery");
		request.setAttribute("parameterurl", parameterurl);
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
	
	public String update() throws ParseException{
		Com_info com = new Com_info();
		com.setCid(Integer.valueOf(request.getParameter("cid")));
		com.setCname(request.getParameter("cname"));
		com.setCfield(request.getParameter("cfield"));
		com.setCprov(request.getParameter("cprov"));
		com.setCcity(request.getParameter("ccity"));
		com.setCtype(request.getParameter("ctype"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		com.setStartdate(sdf.parse(request.getParameter("startdate")));
		com.setEnddate(sdf.parse(request.getParameter("enddate")));
		ComDAO cdao = new ComDAOImpl();
		if(cdao.updateCom(com)){
			return "update_success";
		}else return "update_error";
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
	
	public void exportCom2Excel(List<Com_info> list, String filepath, String filename){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("单位情况表");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("单位名称");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("所属行业");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("单位性质");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("所在省");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("所在市");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("招聘起始");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("招聘结束");
		cell.setCellStyle(style);
		
		for(int i = 1; i <= list.size(); i++){
			row = sheet.createRow(i);
			Com_info com = list.get(i-1);
			row.createCell(0).setCellValue(com.getCname());
			row.createCell(1).setCellValue(com.getCfield());
			row.createCell(2).setCellValue(com.getCtype());
			row.createCell(3).setCellValue(com.getCprov());
			row.createCell(4).setCellValue(com.getCcity());
			row.createCell(5).setCellValue(com.getStartdate());
			row.createCell(6).setCellValue(com.getEnddate());
		}
		
		try{
			ByteArrayOutputStream wbout = new ByteArrayOutputStream();
			wb.write(wbout);
			inputStream = new ByteArrayInputStream(wbout.toByteArray());
			wb.close();
		}catch(Exception e){
			e.printStackTrace();
			inputStream = new ByteArrayInputStream("输出错误".getBytes());
		}

	}
}
