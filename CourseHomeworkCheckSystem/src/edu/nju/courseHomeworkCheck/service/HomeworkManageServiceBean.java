package edu.nju.courseHomeworkCheck.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.nju.courseHomeworkCheck.dao.HomeworkDao;
import edu.nju.courseHomeworkCheck.models.Homework;
import edu.nju.courseHomeworkCheck.models.HomeworkGrade;
import edu.nju.courseHomeworkCheck.models.HomeworkUpload;

@Service 
public class HomeworkManageServiceBean implements HomeworkManageService {

//	HomeworkDao homeworkDao = (HomeworkDao) EJBFactory
//			.getEJB("ejb:/CourseHomeworkCheckSystemEJB//HomeworkDaoImpl!edu.nju.courseHomeworkCheck.dao.HomeworkDao");
//
//	StudentDao studentDao = (StudentDao) EJBFactory
//			.getEJB("ejb:/CourseHomeworkCheckSystemEJB//StudentDaoImpl!edu.nju.courseHomeworkCheck.dao.StudentDao");

//	@EJB StudentDao studentDao;
//	@EJB HomeworkDao homeworkDao;
	
//	private static HomeworkDao homeworkDao=DaoFactory.getHomeworkDao();
//	private static HomeworkManageServiceBean homeworkService=new HomeworkManageServiceBean();
//	
//	private HomeworkManageServiceBean()
//	{
//		
//	}
//	
//	public static HomeworkManageServiceBean getInstance()
//	{
//		return homeworkService;
//	}
	
	@Autowired
	private HomeworkDao homeworkDao;
	
	
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
	public List findByStudent(String studentid) {
		return homeworkDao.findByStudent(studentid);
	}

	@SuppressWarnings("rawtypes")
	public List findUploadConditionByStudent(String studentid) {
		return homeworkDao.findUploadConditionByStudent(studentid);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findUnUploadByStudent(String studentid) {
		List allHomework = homeworkDao.findByStudent(studentid);
		List l = homeworkDao.findUploadConditionByStudent(studentid);
		ArrayList<HomeworkUpload> upload = new ArrayList<HomeworkUpload>(l);
		ArrayList<Homework> unUpload = new ArrayList<Homework>();
		boolean isUploaded = false;
		for(int i=0;i<allHomework.size();i++){
			Homework homework = (Homework) allHomework.get(i);
			for(HomeworkUpload h:upload){
				if(homework.getHomeworkid()==h.getHomework().getHomeworkid()){
					isUploaded = true;
					break;
				}
			}
			if(!isUploaded){
				unUpload.add(homework);
			}
			isUploaded = false;
		}
		return unUpload;
	}

	@SuppressWarnings("rawtypes")
	public List findGradeByStudent(String studentid) {
		return homeworkDao.findGradeByStudent(studentid);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findFailedGradeByStudent(String studentid) {
		List allGrade = homeworkDao.findGradeByStudent(studentid);
		ArrayList<HomeworkGrade> all = new ArrayList<HomeworkGrade>(allGrade);
		ArrayList<HomeworkGrade> failed = new ArrayList<HomeworkGrade>();
		for(HomeworkGrade hg:all){
			if(hg.getGrade()<60){
				failed.add(hg);
			}
		}
		return failed;
	}

	@SuppressWarnings("rawtypes")
	public List findByCourse(int courseid) {
		return homeworkDao.findByCourse(courseid);
	}

}
