package service;

import java.util.List;

import entity.Com_info;

public interface ComDAO {
	public List<Com_info> queryAllComs();
	public Com_info queryComBySid(String sid);
	public boolean addCom(Com_info s);
	public boolean updateCom(Com_info s);
	public boolean deleteCom(String sid);
}
