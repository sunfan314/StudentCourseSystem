package edu.cn.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *courseId		课程id
 *studentId		学生id
 */
@Entity
@Table(name = "course_student")
@IdClass(edu.cn.model.CourseStudentPK.class)
public class CourseStudent {
	@Id
	private int courseId;

	@Id
	private int studentId;

	@Transient
	private String username;

	@Transient
	private String studentName;

	private String score;

	public CourseStudent() {
		super();
	}

	public CourseStudent(int courseId, int studentId) {
		super();
		this.courseId = courseId;
		this.studentId = studentId;
		this.score="";
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	
}
