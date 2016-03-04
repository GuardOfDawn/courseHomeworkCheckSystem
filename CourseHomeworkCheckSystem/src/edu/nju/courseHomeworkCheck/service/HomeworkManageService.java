package edu.nju.courseHomeworkCheck.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("rawtypes")
public interface HomeworkManageService {

	public void sentErrorMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void sentMessage(String message,HttpServletRequest req) 
			throws ServletException,IOException;

	public void forwardPage(String page,HttpServletRequest req,HttpServletResponse resp) 
			throws ServletException,IOException;
	
	public List findByStudent(String studentid);
	
	public List findUploadConditionByStudent(String studentid);
	
	public List findUnUploadByStudent(String studentid);
	
	public List findGradeByStudent(String studentid);
	
	public List findFailedGradeByStudent(String studentid);
	
	public List findByCourse(int courseid);
}
