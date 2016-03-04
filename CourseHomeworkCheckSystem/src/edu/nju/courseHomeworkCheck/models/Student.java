package edu.nju.courseHomeworkCheck.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student implements Serializable{
	/**
	 * 
	 */
	private String studentid;
	private String nickname;
	private int age;
	private int gender;
	private String password;
	private Set<Course> courses = new HashSet<Course>();
	private Set<Homework> homeworkUpload = new HashSet<Homework>();
	private Set<Homework> homeworkGrade = new HashSet<Homework>();

	@Id
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	
	@Column(name="nickname",nullable=false,length=20)
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Column(name="age",nullable=false)
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	@Column(name="gender",nullable=false)
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name="password",nullable=false,length=20)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@ManyToMany(mappedBy="students")
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	@ManyToMany(mappedBy="studentsUpload")
	public Set<Homework> getHomeworkUpload() {
		return homeworkUpload;
	}
	public void setHomeworkUpload(Set<Homework> homeworkUpload) {
		this.homeworkUpload = homeworkUpload;
	}
	
	@ManyToMany(mappedBy="studentsGrade")
	public Set<Homework> getHomeworkGrade() {
		return homeworkGrade;
	}
	public void setHomeworkGrade(Set<Homework> homeworkGrade) {
		this.homeworkGrade = homeworkGrade;
	}
	
}
