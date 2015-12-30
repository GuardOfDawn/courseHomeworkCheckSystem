package edu.nju.courseHomeworkCheck.models;

public class Homework {
	private int homeworkid;
	private String courseName;
	private String homeworkTitle;
	private String homeworkContent;
	private String dueTime;
	private String uploadTime;
	private String completition;
	private int grade;
	
	public int getHomeworkid() {
		return homeworkid;
	}
	public void setHomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getHomeworkTitle() {
		return homeworkTitle;
	}
	public void setHomeworkTitle(String homeworkTitle) {
		this.homeworkTitle = homeworkTitle;
	}
	public String getHomeworkContent() {
		return homeworkContent;
	}
	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getCompletition() {
		return completition;
	}
	public void setCompletition(String completition) {
		this.completition = completition;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
}
