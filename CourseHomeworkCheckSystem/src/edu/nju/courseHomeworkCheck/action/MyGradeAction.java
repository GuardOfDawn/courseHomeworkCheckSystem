package edu.nju.courseHomeworkCheck.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.courseHomeworkCheck.action.business.HomeworkGradeListBean;
import edu.nju.courseHomeworkCheck.service.HomeworkManageService;

@Controller
public class MyGradeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private HomeworkManageService homeworkManage;
	
	@SuppressWarnings("unchecked")
	public String execute(){
		if(session.get("studentId")==null){
			return INPUT;
		}
		else{
			if(session.get("listGrade")==null){
				String studentid = String.valueOf(session.get("studentId"));
				HomeworkGradeListBean listGrade = new HomeworkGradeListBean();
				listGrade.setHomeworkGradeList(homeworkManage.findGradeByStudent(studentid));
				session.put("listGrade", listGrade);
			}
			return SUCCESS;
		}
	}
	
}
