package edu.nju.courseHomeworkCheck.dao;

import edu.nju.courseHomeworkCheck.models.Student;

public interface StudentDao {
	
	public Student findById(String studentid);
	
	
	public void save(Student student);
	
	
	public boolean updateStudent(Student student);
	
}
