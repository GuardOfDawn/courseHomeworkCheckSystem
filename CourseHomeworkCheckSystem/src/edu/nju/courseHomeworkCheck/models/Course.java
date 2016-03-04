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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name="course")
public class Course implements Serializable{
	/**
	 * 
	 */
	private int courseid;
	private String courseName;
	private String courseTerm;
	private Set<Student> students = new HashSet<Student>();
	private Set<Homework> homeworks = new HashSet<Homework>();

	@Id
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	
	@Column(name="courseName",nullable=false,length=30)
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	@Column(name="courseTerm",nullable=false,length=30)
	public String getCourseTerm() {
		return courseTerm;
	}
	public void setCourseTerm(String courseTerm) {
		this.courseTerm = courseTerm;
	}
	
	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(
	name="courseselection",
	joinColumns=
	@JoinColumn(name="courseid", referencedColumnName="courseid"),
	inverseJoinColumns=
	@JoinColumn(name="studentid", referencedColumnName="studentid")
	)
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	@OneToMany(mappedBy="course", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@OrderBy(value="homeworkid ASC")
	public Set<Homework> getHomeworks() {
		return homeworks;
	}
	public void setHomeworks(Set<Homework> homeworks) {
		this.homeworks = homeworks;
	}
}
