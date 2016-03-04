package edu.nju.courseHomeworkCheck.action.business;

import java.io.Serializable;
import java.util.List;

import edu.nju.courseHomeworkCheck.models.Course;

public class CourseListBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("rawtypes")
	private List courseList;

	@SuppressWarnings("rawtypes")
	public List getCourseList() {
		return courseList;
	}

	@SuppressWarnings("rawtypes")
	public void setCourseList(List courseList) {
		this.courseList = courseList;
	}
	
	public Course getCourseList(int index){
		return (Course) courseList.get(index);
	}
	
}
