package edu.nju.courseHomeworkCheck.service;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.courseHomeworkCheck.dao.CourseDao;

@Service 
public class CourseManageServiceBean implements CourseManageService{

////	@EJB CourseDao courseDao;
//	private static CourseDao courseDao=DaoFactory.getCourseDao();
//	private static CourseManageServiceBean courseService=new CourseManageServiceBean();
//
//	private CourseManageServiceBean()
//	{
//		
//	}
//	
//	public static CourseManageServiceBean getInstance()
//	{
//		return courseService;
//	}
	
	@Autowired
	private CourseDao courseDao;
	
	
	public void sentErrorMessage(String message, HttpServletRequest req) throws ServletException, IOException {
		req.setAttribute("message",message);
	}

	public void sentMessage(String message, HttpServletRequest req) throws ServletException, IOException {
		req.setAttribute("message",message);
	}

	public void forwardPage(String page, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher dispater=req.getRequestDispatcher(resp.encodeURL(page));
		dispater.forward(req,resp);	
	}

	@SuppressWarnings("rawtypes")
	public List findCourseByStudent(String studentid) {
		return courseDao.findByStudent(studentid);
	}

}
