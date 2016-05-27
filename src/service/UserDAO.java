package service;

import entity.User;

public interface UserDAO {
	public boolean userLogin(User u);
	public void changepwd(String username, String newpwd);
}
