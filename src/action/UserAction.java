package action;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ModelDriven;

import entity.User;
import service.UserDAO;
import service.impl.UserDAOImpl;

public class UserAction extends SuperAction implements ModelDriven<User>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	public String login(){
		UserDAO udao = new UserDAOImpl();
		if(udao.userLogin(user)){
			session.setAttribute("LoginUsername", user.getUsername());
			return "login_success";
		}else{
			return "login_failure";
		}
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		if(user == null){
			user = new User();
		}
		return user;
	}
	
	@SkipValidation
	public String logout(){
		if(session.getAttribute("LoginUsername") != null){
			session.setAttribute("", null);
		}
		return "logout_success";
	}
	
	@Override
	public void validate() {
		// TODO Auto-generated method stub
		System.out.println("进入校验程序");
		if("".equals(user.getUsername().trim())){
			this.addFieldError("usernameError", "用户名不能为空");
		}
		if(user.getPassword().length() < 3){
			this.addFieldError("passwordError", "密码长度不少于3位");
		}
	}
	
}
