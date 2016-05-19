package service;

import java.util.List;

import entity.Com_hire;

public interface HireDAO {
	public List<Com_hire> queryAllRecords();
	public Com_hire queryHireBySid(String sid);
	public boolean addHire(Com_hire s);
	public boolean updateHire(Com_hire s);
	public boolean deleteHire(String sid);
}
