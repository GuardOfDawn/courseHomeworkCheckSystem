package edu.nju.courseHomeworkCheck.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="homework")
public class Homework implements Serializable{
	
	private int homeworkid;
	private String homeworktitle;
	private String homeworkContent;
	private String dueTime;
	private Course course;
	private Set<Student> studentsUpload = new HashSet<Student>();
	private Set<Student> studentsGrade = new HashSet<Student>();
	
	
	@Id
	public int getHomeworkid() {
		return homeworkid;
	}
	public void setHomeworkid(int homeworkid) {
		this.homeworkid = homeworkid;
	}

	@Column(name="homeworktitle",nullable=false,length=20)
	public String getHomeworktitle() {
		return homeworktitle;
	}
	public void setHomeworktitle(String homeworktitle) {
		this.homeworktitle = homeworktitle;
	}

	@Column(name="homeworkContent",nullable=false,length=300)
	public String getHomeworkContent() {
		return homeworkContent;
	}
	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}

	@Column(name="dueTime",nullable=false)
	public String getDueTime() {
		return dueTime;
	}
	public void setDueTime(String dueTime) {
		this.dueTime = dueTime;
	}

	@ManyToOne(cascade=CascadeType.ALL, optional=false)
	@JoinColumn(name="courseid")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
	name="homeworkupload",
	joinColumns=
	@JoinColumn(name="homeworkid", referencedColumnName="homeworkid"),
	inverseJoinColumns=
	@JoinColumn(name="studentid", referencedColumnName="studentid")
	)
	public Set<Student> getStudentsUpload() {
		return studentsUpload;
	}
	public void setStudentsUpload(Set<Student> studentsUpload) {
		this.studentsUpload = studentsUpload;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
	name="homeworkgrade",
	joinColumns=
	@JoinColumn(name="homeworkid", referencedColumnName="homeworkid"),
	inverseJoinColumns=
	@JoinColumn(name="studentid", referencedColumnName="studentid")
	)
	public Set<Student> getStudentsGrade() {
		return studentsGrade;
	}
	public void setStudentsGrade(Set<Student> studentsGrade) {
		this.studentsGrade = studentsGrade;
	}
}
