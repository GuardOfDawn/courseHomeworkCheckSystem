package edu.nju.courseHomeworkCheck.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.courseHomeworkCheck.dao.StudentDao;
import edu.nju.courseHomeworkCheck.models.Student;

@Service 
public class StudentManageServiceBean implements StudentManageService{
	
////	@EJB StudentDao studentDao;
//	private static StudentDao studentDao=DaoFactory.getStudentDao();
//	private static StudentManageServiceBean studentService=new StudentManageServiceBean();
//	
//	private StudentManageServiceBean()
//	{
//		
//	}
//	
//	public static StudentManageServiceBean getInstance()
//	{
//		return studentService;
//	}
	
//	StudentDao studentDao = (StudentDao) EJBFactory
//			.getEJB("ejb:/CourseHomeworkCheckSystemEJB//StudentDaoImpl!edu.nju.courseHomeworkCheck.dao.StudentDao");

	@Autowired
	private StudentDao studentDao;
	

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

	public boolean Login(String student, String password) {
		Student s = studentDao.findById(student);
		if(s!=null&&s.getPassword().equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Student findStudent(String id){
		return studentDao.findById(id);
	}

}
