package service;

import java.util.List;

import entity.Student_school;

public interface StudentschoolDAO {
	public List<Student_school> queryAllRecords();
	public List<Student_school> queryRecordBySid(String sid);
	public Integer addRecord(Student_school s);
	public boolean updateRecord(Student_school s);
	public boolean deleteRecord(String ssid);
}
