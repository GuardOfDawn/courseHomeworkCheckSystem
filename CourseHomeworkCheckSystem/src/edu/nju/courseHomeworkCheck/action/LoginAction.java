package edu.nju.courseHomeworkCheck.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.courseHomeworkCheck.action.business.HomeworkGradeListBean;
import edu.nju.courseHomeworkCheck.action.business.HomeworkListBean;
import edu.nju.courseHomeworkCheck.models.Student;
import edu.nju.courseHomeworkCheck.service.HomeworkManageService;
import edu.nju.courseHomeworkCheck.service.StudentManageService;

@Controller
public class LoginAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private StudentManageService studentManage;
	@Autowired
	private HomeworkManageService homeworkManage;
	private Student student;
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("studentId")==null){
			String loginValue = request.getParameter("studentId");
			boolean isLoginAction = (null == loginValue) ? false : true;
			if (isLoginAction) {
				boolean loginRes = studentManage.Login(loginValue, request.getParameter("password"));
				if(loginRes){
					student = studentManage.findStudent(loginValue);
					session.put("studentId", loginValue);
					session.put("student", student);
					//获取作业提醒和成绩提醒信息
					HomeworkListBean listHomeworkUnUpload = new HomeworkListBean();
					listHomeworkUnUpload.setHomeworkList(homeworkManage.findUnUploadByStudent(loginValue));
					HomeworkGradeListBean listHomeworkFailed = new HomeworkGradeListBean();
					listHomeworkFailed.setHomeworkGradeList(homeworkManage.findFailedGradeByStudent(loginValue));
					session.put("listHomeworkUnUpload", listHomeworkUnUpload);
					session.put("listHomeworkFailed", listHomeworkFailed);
					return SUCCESS;
				}
				else{
					return ERROR;
				}
			}
			else{
				return INPUT;
			}
		}
		else{
			return SUCCESS;
		}
	}

}
