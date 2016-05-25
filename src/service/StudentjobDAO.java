package service;

import java.util.List;

import entity.Student_job;
import entity.Student_school;

public interface StudentjobDAO {
	public List<Student_job> queryAllRecords();
	public List<Student_job> queryRecordBySid(String sid);
	public Integer addRecord(Student_job s);
	public boolean updateRecord(Student_job s);
	public boolean deleteRecord(String sjid);
	public List<Student_job> queryFilter(String time, String type, String cname, String job, String comment, int page, boolean flag);
	public int queryFilter(String time, String type, String cname, String job, String comment);
}
