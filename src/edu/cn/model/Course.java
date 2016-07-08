package edu.cn.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *courseId 			课程id
 *courseName		课程名
 *optional			是否选修
 *maxNum			最大选修人数
 *time				上课时间
 *place				上课地点
 *hour				学时
 *credit			学分
 */
@Entity
@Table(name = "course")
public class Course {
	@Id
	private int courseId;

	private String courseName;

	private String optional;

	private int maxNum;

	private String time;

	private String place;

	private int hour;

	private int credit;

	@Transient
	private String score;

	@Transient
	private int creditGet;

	@Transient
	private String courseTeachers;

	@Transient
	private int studentNum;

	@Transient
	private String isChosen;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "teacherCourseList", cascade = CascadeType.ALL)
	private List<User> teacherList;

	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "studentCourseList", cascade = CascadeType.ALL)
	private List<User> studentList;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getOptional() {
		return optional;
	}

	public void setOptional(String optional) {
		this.optional = optional;
	}

	public int getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(int maxNum) {
		this.maxNum = maxNum;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getCreditGet() {
		return creditGet;
	}

	public void setCreditGet(int creditGet) {
		this.creditGet = creditGet;
	}

	public String getCourseTeachers() {
		return courseTeachers;
	}

	public void setCourseTeachers(String courseTeachers) {
		this.courseTeachers = courseTeachers;
	}

	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getIsChosen() {
		return isChosen;
	}

	public void setIsChosen(String isChosen) {
		this.isChosen = isChosen;
	}

	public List<User> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<User> teacherList) {
		this.teacherList = teacherList;
	}

	public List<User> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<User> studentList) {
		this.studentList = studentList;
	}

}
