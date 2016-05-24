package action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.Criteria;

import entity.Student_info;
import entity.Student_job;
import entity.Student_school;
import service.StudentDAO;
import service.StudentjobDAO;
import service.StudentschoolDAO;
import service.impl.StudentDAOImpl;
import service.impl.StudentjobDAOImpl;
import service.impl.StudentschoolDAOImpl;

public class StudentAction extends SuperAction{
	private InputStream inputStream;
	
	public InputStream getInputStream(){
		return inputStream;
	}
	public String query(){
		String casetype = request.getParameter("casetype");
		boolean flag = false;
		if(casetype != null && casetype.equals("1")) flag = true;
		
		String pagestr = request.getParameter("page") == null || "".equals(request.getParameter("page")) ? "1": request.getParameter("page");
		int pagenum = Integer.valueOf(pagestr);	
		StudentDAO sdao = new StudentDAOImpl();
		//List<Student_info> list = sdao.queryAllStudents();
		List<Student_info> list = sdao.queryRecordsByPage(pagenum, flag);
		request.setAttribute("students_list", list);
		request.setAttribute("methodurl", "query");
		
		int rowsnum = sdao.getRowsnum(), pagesize = 0;
		if(rowsnum % 12 == 0){
			pagesize = rowsnum / 12;
		}else pagesize = rowsnum / 12 + 1;
		
		if(flag){
			exportStu2Excel(list, "", "");	
			return "export_success";
		}
		
		int beforepage = pagenum - 1;
		int afterpage = pagenum + 1;
		beforepage = beforepage > 0? beforepage : 1;
		afterpage = afterpage <= pagesize? afterpage : pagesize; 
		
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("beforepage", beforepage);
		request.setAttribute("afterpage", afterpage);
		
		return "query_success";
	}
	
	public String add(){
		StudentDAO sdao = new StudentDAOImpl();
		Student_info s = new Student_info();
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String gender = request.getParameter("gender");
		String political = request.getParameter("political");
		String sprov = request.getParameter("sprov");
		String scity = request.getParameter("scity");
		String major = request.getParameter("major");
		String tel = request.getParameter("tel");
		String sqq = request.getParameter("sqq");
		s.setSid(sid);
		s.setSname(sname);
		s.setGender(gender);
		s.setPolitical(political);
		s.setSprov(sprov);
		s.setScity(scity);
		s.setMajor(major);
		s.setTel(tel);
		s.setSqq(sqq);
	    s.setSid(sid);
		if(sdao.addStudent(s)){
			return "add_success";
		}
		return "add_error";
	}
	
	public void validateAdd(){
		System.out.println("进入校验");
		String sid = request.getParameter("sid");
		if(sid == null || "".equals(sid)){
			this.addFieldError("usernameError", "学号不能为空");
		}
		String sname = request.getParameter("sname");
		if(sname == null || "".equals(sname)){
			this.addFieldError("usernameError", "姓名不能为空");
		}
	}
	
	public String modify(){
		String sid = request.getParameter("sid");
		//基本信息
		StudentDAO sdao = new StudentDAOImpl();
		Student_info s = sdao.queryStudentBySid(sid);
		request.setAttribute("modify_students", s);
		
		StudentschoolDAO stuschdao = new StudentschoolDAOImpl();
		List<Student_school> stusch = stuschdao.queryRecordBySid(sid);
		request.setAttribute("records_list", stusch);
        
		StudentjobDAO stujobdao = new StudentjobDAOImpl();
		List<Student_job> stujob = stujobdao.queryRecordBySid(sid);
		request.setAttribute("jobrecords_list", stujob);
		
		return "modify_success";
	}
	
	public String update(){
		StudentDAO sdao = new StudentDAOImpl();
		Student_info s = new Student_info();
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String gender = request.getParameter("gender");
		String political = request.getParameter("political");
		String sprov = request.getParameter("sprov");
		String scity = request.getParameter("scity");
		String major = request.getParameter("major");
		String tel = request.getParameter("tel");
		String sqq = request.getParameter("sqq");
		s.setSid(sid);
		s.setSname(sname);
		s.setGender(gender);
		s.setPolitical(political);
		s.setSprov(sprov);
		s.setScity(scity);
		s.setMajor(major);
		s.setTel(tel);
		s.setSqq(sqq);
		if(sdao.updateStudent(s)){
			return "update_success";
		}else{
			return "update_error";
		}
	}
	
	public String delete(){
		StudentDAO sdao = new StudentDAOImpl();
		String sid = request.getParameter("sid");
		sdao.deleteStudent(sid);
		return "delete_success";
	}
	
	public String schoolquery(){
		StudentschoolDAO sdao = new StudentschoolDAOImpl();
		List<Student_school> list = sdao.queryAllRecords();
		request.setAttribute("records_list", list);
		return "school_query_success";
	}
	
	public String schooldelete(){
		String ssid = request.getParameter("ssid");
		StudentschoolDAO sdao = new StudentschoolDAOImpl();
		sdao.deleteRecord(ssid);
		return "school_delete_success";
	}
	
	public String schoolajaxdelete() throws Exception{
		String ssid = request.getParameter("ssid");
		StudentschoolDAO sdao = new StudentschoolDAOImpl();
		if(sdao.deleteRecord(ssid)){
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}
		return SUCCESS;
	}
	
	public String jobajaxdelete() throws Exception{
		String sjid = request.getParameter("sjid");
		StudentjobDAO sdao = new StudentjobDAOImpl();
		if(sdao.deleteRecord(sjid)){
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("1".getBytes("UTF-8"));
		}
		return SUCCESS;
	}
	
	public String schooladd() throws Exception{
		Map<String, String[]> map = request.getParameterMap();
		String sid = map.get("sid")[0];
		String sname = map.get("sname")[0];
		String activity = map.get("activity")[0];
		String honor = map.get("honor")[0];
		String startyear = map.get("startyear")[0];
		String endyear = map.get("endyear")[0];
		Student_school ssch = new Student_school(0, sid, sname, activity, honor, startyear, endyear);
		System.out.println(ssch);
		StudentschoolDAO studao = new StudentschoolDAOImpl();
		Integer ssid = studao.addRecord(ssch);
		if(ssid != 0){
			inputStream = new ByteArrayInputStream(String.valueOf(ssid).getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		
		return "success";
	}
	
	public String schoolupdate(){
		return "school_update";
	}
	
	public String jobquery(){
		StudentjobDAO sdao = new StudentjobDAOImpl();
		List<Student_job> list = sdao.queryAllRecords();
		request.setAttribute("records_list", list);
		return "job_query_success";
	}
	
	public String jobadd() throws Exception{
		Map<String, String[]> map = request.getParameterMap();
		String sid = map.get("sid")[0];
		String sname = map.get("sname")[0];
		String time = map.get("time")[0];
		String type = map.get("type")[0];
		String cname = map.get("cname")[0];
		String job = map.get("job")[0];
		String comment = map.get("comment")[0];
		Student_job stujob = new Student_job(0, sid, sname, time, type, cname, job, comment);
		StudentjobDAO sjdao = new StudentjobDAOImpl();
		Integer sjid = sjdao.addRecord(stujob);
		if(sjid != 0){
			inputStream = new ByteArrayInputStream(String.valueOf(sjid).getBytes("UTF-8"));
		}else{
			inputStream = new ByteArrayInputStream("0".getBytes("UTF-8"));
		}
		return SUCCESS;
	}
	
	public String jobdelete(){
		String sjid = request.getParameter("sjid");
		StudentjobDAO sjdao = new StudentjobDAOImpl();
		sjdao.deleteRecord(sjid);
		return "job_delete_success";
	}
	
	public String queryByInfo() throws Exception{
		String casetype = request.getParameter("casetype");
		boolean flag = false;
		if(casetype != null && casetype.equals("1")) flag = true;
		
		String sid = request.getParameter("sid");
		String sname = request.getParameter("sname");
		String gender = request.getParameter("gender");
		String political = request.getParameter("political");
		String sprov = request.getParameter("sprov");
		String scity = request.getParameter("scity");
		String tel = request.getParameter("tel");
		String sqq = request.getParameter("sqq");
		
		String parameterurl = "sid=" + sid + "&sname=" + sname +"&gender=" + gender
				+ "&political=" + political + "&sprov=" + sprov + "&scity=" + scity + "&tel="
				+ tel + "&sqq=" + sqq;
		
		String pagestr = request.getParameter("page") == null || "".equals(request.getParameter("page")) ? "1": request.getParameter("page");		
		int pagenum = Integer.valueOf(pagestr);	
		StudentDAO sdao = new StudentDAOImpl();
		List<Student_info> list = null;
		if("".equals(sid) && "".equals(sname) && "".equals(gender) && "".equals(political) && "".equals(sprov)
				&& "".equals(scity) && "".equals(tel) && "".equals(sqq)){
			list = sdao.queryRecordsByPage(pagenum, flag);
		}else{
			list = sdao.queryFilter(sid, sname, gender, political, sprov, scity, tel, sqq, pagenum, flag);
		}	
		
		if(flag){
			exportStu2Excel(list, "", "");	
			return "";
		}
		
		request.setAttribute("students_list", list);
		int rowsnum = list.size(), pagesize = 0;
		if(rowsnum % 12 == 0){
			pagesize = rowsnum / 12;
		}else pagesize = rowsnum / 12 + 1;
		
		int beforepage = pagenum - 1;
		int afterpage = pagenum + 1;
		beforepage = beforepage > 0? beforepage : 1;
		afterpage = afterpage <= pagesize? afterpage : pagesize; 
		
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("beforepage", beforepage);
		request.setAttribute("afterpage", afterpage);
		
		request.setAttribute("methodurl", "queryByInfo");
		request.setAttribute("parameterurl", parameterurl);
		
		return "query_success";
	}
	
	public String queryBySchool(){
		String casetype = request.getParameter("casetype");
		boolean flag = false;
		if(casetype != null && casetype.equals("1")) flag = true;
		
		String activity = request.getParameter("activity");
		String honor = request.getParameter("honor");
		String startyear = request.getParameter("startyear");
		String endyear = request.getParameter("endyear");
		
		String pagestr = request.getParameter("page") == null || "".equals(request.getParameter("page")) ? "1": request.getParameter("page");		
		int pagenum = Integer.valueOf(pagestr);	
		StudentDAO sdao = new StudentDAOImpl();
		List<Student_info> list = null;
		
		if("".equals(activity) || "".equals(honor) || "".equals(startyear) || "".equals(endyear)){
			list = sdao.queryRecordsByPage(pagenum, flag);
		}else{
			list = sdao.queryFilterSchool(activity, honor, startyear, endyear, pagenum, flag);
		}
		
		request.setAttribute("students_list", list);
		int rowsnum = list.size(), pagesize = 0;
		if(rowsnum % 12 == 0){
			pagesize = rowsnum / 12;
		}else pagesize = rowsnum / 12 + 1;
		
		int beforepage = pagenum - 1;
		int afterpage = pagenum + 1;
		beforepage = beforepage > 0? beforepage : 1;
		afterpage = afterpage <= pagesize? afterpage : pagesize; 
		
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("beforepage", beforepage);
		request.setAttribute("afterpage", afterpage);
		
		return "query_success";
	}
	
	public String queryByJob(){
		String casetype = request.getParameter("casetype");
		boolean flag = false;
		if(casetype != null && casetype.equals("1")) flag = true;
		
		String time = request.getParameter("time");
		String type = request.getParameter("type");
		String cname = request.getParameter("cname");
		String job = request.getParameter("job");
		String comment = request.getParameter("comment");
		
		String pagestr = request.getParameter("page") == null || "".equals(request.getParameter("page")) ? "1": request.getParameter("page");		
		int pagenum = Integer.valueOf(pagestr);	
		StudentDAO sdao = new StudentDAOImpl();
		List<Student_info> list = null;
		
		if("".equals(time) || "".equals(type) || "".equals(cname) || "".equals(job) || "".equals(comment)){
			list = sdao.queryRecordsByPage(pagenum, flag);
		}else{
			list = sdao.queryFilterJob(time, type, cname, job, comment, pagenum, flag);
		}
		
		request.setAttribute("students_list", list);
		int rowsnum = list.size(), pagesize = 0;
		if(rowsnum % 12 == 0){
			pagesize = rowsnum / 12;
		}else pagesize = rowsnum / 12 + 1;
		
		int beforepage = pagenum - 1;
		int afterpage = pagenum + 1;
		beforepage = beforepage > 0? beforepage : 1;
		afterpage = afterpage <= pagesize? afterpage : pagesize; 
		
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("pagenum", pagenum);
		request.setAttribute("beforepage", beforepage);
		request.setAttribute("afterpage", afterpage);
		
		return "query_success";
	}
	
	public void exportStu2Excel(List<Student_info> list, String filepath, String filename){
		// TODO Auto-generated method stub
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("学生表");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("学号");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("姓名");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("性别");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("政治面貌");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("省市");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("专业");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("电话");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("QQ");
		cell.setCellStyle(style);
		
		for(int i = 1; i <= list.size(); i++){
			row = sheet.createRow(i);
			Student_info stu = list.get(i-1);
			row.createCell(0).setCellValue(stu.getSid());
			row.createCell(1).setCellValue(stu.getSname());
			row.createCell(2).setCellValue(stu.getGender());
			row.createCell(3).setCellValue(stu.getPolitical());
			row.createCell(4).setCellValue(stu.getSprov() + "" + stu.getScity());
			row.createCell(5).setCellValue(stu.getMajor());
			row.createCell(6).setCellValue(stu.getTel());
			row.createCell(7).setCellValue(stu.getSqq());
		}
		
		try{
			/*FileOutputStream out = new FileOutputStream(filepath + filename);
			wb.write(out);
			out.close();*/
			//wb.write(response.getOutputStream());
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
