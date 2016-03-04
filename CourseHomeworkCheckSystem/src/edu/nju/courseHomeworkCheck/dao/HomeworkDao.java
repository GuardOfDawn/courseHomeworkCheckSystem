package edu.nju.courseHomeworkCheck.dao;

import java.util.List;

import edu.nju.courseHomeworkCheck.models.Homework;

@SuppressWarnings("rawtypes")
public interface HomeworkDao {
	
	public List findByCourse(int courseid);
	
	public Homework findById(int homeworkId);
	
	public boolean updateHomework(Homework homework);
	
	public List findByStudent(String studentid);
	
	public List findUploadConditionByStudent(String studentid);
//	
//	public List findUnUploadByStudent(String studentid);
//	
	public List findGradeByStudent(String studentid);
//	
//	public List findFailedGradeByStudent(String studentid);
	
//	public List find();
	
}
