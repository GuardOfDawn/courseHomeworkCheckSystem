package edu.nju.courseHomeworkCheck.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.courseHomeworkCheck.action.business.CourseListBean;
import edu.nju.courseHomeworkCheck.service.CourseManageService;

@Controller
public class MyCourseAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private CourseManageService courseManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session==null||session.get("studentId")==null){
			return INPUT;
		}
		else{
			if(session.get("listCourse")==null){
				String studentid = String.valueOf(session.get("studentId"));
				CourseListBean courseList = new CourseListBean();
				courseList.setCourseList(courseManage.findCourseByStudent(studentid));
				session.put("listCourse", courseList);
			}
			return SUCCESS;
		}
	}

}
