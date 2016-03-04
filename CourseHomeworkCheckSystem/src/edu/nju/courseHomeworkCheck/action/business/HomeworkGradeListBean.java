package edu.nju.courseHomeworkCheck.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.courseHomeworkCheck.models.HomeworkGrade;

public class HomeworkGradeListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	private List homeworkGradeList;

	@SuppressWarnings("rawtypes")
	public List getGradeList() {
		return homeworkGradeList;
	}

	@SuppressWarnings("rawtypes")
	public void setHomeworkGradeList(List gradeList) {
		this.homeworkGradeList = gradeList;
	}
	
	public HomeworkGrade getHomeworkGradeList(int index){
		return (HomeworkGrade) homeworkGradeList.get(index);
	}

}
