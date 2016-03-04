package edu.nju.courseHomeworkCheck.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import edu.nju.courseHomeworkCheck.action.business.HomeworkUploadListBean;
import edu.nju.courseHomeworkCheck.service.HomeworkManageService;

@Controller
public class MyHomeworkAction extends BaseAction{

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
			if(session.get("listHomeworkUpload")==null){
				String studentid = String.valueOf(session.get("studentId"));
				HomeworkUploadListBean listHomeworkUpload = new HomeworkUploadListBean();
				listHomeworkUpload.setHomeworkList(homeworkManage.findUploadConditionByStudent(studentid));
				session.put("listHomeworkUpload", listHomeworkUpload);
			}
			return SUCCESS;
		}
	}

}
