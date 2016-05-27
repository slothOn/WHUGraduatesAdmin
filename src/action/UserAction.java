package action;

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

	public String logout(){
		if(session.getAttribute("LoginUsername") != null){
			session.setAttribute("", null);
		}
		return "logout_success";
	}
	
	public String changepwd(){
		String newpwd2 = request.getParameter("newpwd2");
		UserDAO udao = new UserDAOImpl();
		String username = (String) session.getAttribute("LoginUsername");
		udao.changepwd(username, newpwd2);
		return "logout_success";
	}
	
	public void validateLogin() {
		// TODO Auto-generated method stub
		System.out.println("进入校验程序");
		if("".equals(user.getUsername().trim())){
			this.addFieldError("usernameError", "用户名不能为空");
		}
		if(user.getPassword().length() < 3){
			this.addFieldError("passwordError", "密码长度不少于3位");
		}
	}
	
	public void validateChangepwd(){
		System.out.println("验证原密码");
		String prevpwd = request.getParameter("prevpwd");
		String username = (String) session.getAttribute("LoginUsername");
		UserDAO udao = new UserDAOImpl();
		if(!udao.userLogin(new User(0, username, prevpwd))){
			this.addFieldError("passwordError", "原密码输入错误");
		}
	}
}
