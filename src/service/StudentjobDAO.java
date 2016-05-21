package service;

import java.util.List;

import entity.Student_job;

public interface StudentjobDAO {
	public List<Student_job> queryAllRecords();
	public List<Student_job> queryRecordBySid(String sid);
	public Integer addRecord(Student_job s);
	public boolean updateRecord(Student_job s);
	public boolean deleteRecord(String sjid);
}
