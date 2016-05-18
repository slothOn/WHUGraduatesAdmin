package service;

import java.util.List;

import entity.Student_info;


public interface StudentDAO {
	public List<Student_info> queryAllStudents();
	public Student_info queryStudentBySid(String sid);
	public boolean addStudent(Student_info s);
	public boolean updateStudent(Student_info s);
	public boolean deleteStudent(String sid);
}
