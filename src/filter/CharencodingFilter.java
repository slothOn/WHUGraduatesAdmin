package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
@WebFilter(
	filterName="charencoding",
	urlPatterns={"/*"},
	initParams={
			@WebInitParam(name="loginPage", value="/users/Users_login.jsp"),
			@WebInitParam(name="loginAction", value="/users/User_login.action"),
			@WebInitParam(name="treePage", value="tree.jsp"),
			@WebInitParam(name="loginSuccessPage", value="/users/Users_login_success.jsp"),
			@WebInitParam(name="loginMainPage", value="/users/Users_login_main.jsp"),
			@WebInitParam(name="changepwdPage", value="/users/Users_changepwd.jsp")
	})
public class CharencodingFilter implements Filter{
	private FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String loginPage = config.getInitParameter("loginPage");
		String loginAction = config.getInitParameter("loginAction");
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest requ = (HttpServletRequest)request;
		HttpSession session = requ.getSession();
		String requestpath = requ.getServletPath();
		if(session.getAttribute("LoginUsername") != null &&
			!session.getAttribute("LoginUsername").equals("") &&
			!requestpath.endsWith(loginPage) &&
			!requestpath.endsWith(loginAction)){
			chain.doFilter(request, response);
		}else{
			requ.getRequestDispatcher(loginPage).forward(request, response);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		this.config = config;
	}

}
