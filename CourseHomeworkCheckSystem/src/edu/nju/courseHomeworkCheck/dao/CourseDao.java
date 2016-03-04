package edu.nju.courseHomeworkCheck.dao;

import java.util.List;

import edu.nju.courseHomeworkCheck.models.Course;

public interface CourseDao {
	
	public Course findById(int courseid);
	
	@SuppressWarnings("rawtypes")
	public List findByStudent(String studentid);

//	public void save(Course course);
//	
//	public List find(String column,String value);
//	
//	public List find(String name);
//	
	public boolean updateCourse(Course course);
//	
//	public List find();
//	
//	public List findType();
}
