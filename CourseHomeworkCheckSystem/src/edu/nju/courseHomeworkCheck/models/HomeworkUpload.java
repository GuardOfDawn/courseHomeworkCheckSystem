package edu.nju.courseHomeworkCheck.models;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="homeworkupload")
public class HomeworkUpload implements Serializable{
	private long id;
	private String uploadTime;
	private Homework homework;
	private Student student;
	

	@Id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setIdByDate()
	{
		Calendar datetime=Calendar.getInstance();
		this.id=datetime.getTimeInMillis();
	}
	
	@Column(name="uploadTime",nullable=false)
	public String getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="homeworkid")  
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="studentid")  
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
