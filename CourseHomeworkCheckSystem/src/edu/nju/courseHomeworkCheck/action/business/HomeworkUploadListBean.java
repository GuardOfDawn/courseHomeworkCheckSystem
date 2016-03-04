package edu.nju.courseHomeworkCheck.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.courseHomeworkCheck.models.HomeworkUpload;

public class HomeworkUploadListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private List homeworkList;

	@SuppressWarnings("rawtypes")
	public List getHomeworkList() {
		return homeworkList;
	}

	@SuppressWarnings("rawtypes")
	public void setHomeworkList(List homeworkList) {
		this.homeworkList = homeworkList;
	}
	
	public HomeworkUpload getHomeworkList(int index){
		return (HomeworkUpload) homeworkList.get(index);
	}
	
}
