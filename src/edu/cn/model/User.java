package edu.cn.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *用户类
 *id			用户id		
 *username		用户名
 *password		密码
 *type			用户类型
 *name			用户姓名
 *sex			用户性别	
 *department	用户所在院系
 *phone			用户电话号码
 *email			用户邮箱
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	private int id;

	private String username;

	private String password;

	private int type;

	private String name;

	private String sex;

	private String department;

	private String phone;

	private String email;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "course_teacher", joinColumns = @JoinColumn(name = "teacherId"), inverseJoinColumns = @JoinColumn(name = "courseId"))
	private List<Course> teacherCourseList;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "studentId"), inverseJoinColumns = @JoinColumn(name = "courseId"))
	private List<Course> studentCourseList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Course> getTeacherCourseList() {
		return teacherCourseList;
	}

	public void setTeacherCourseList(List<Course> teacherCourseList) {
		this.teacherCourseList = teacherCourseList;
	}

	public List<Course> getStudentCourseList() {
		return studentCourseList;
	}

	public void setStudentCourseList(List<Course> studentCourseList) {
		this.studentCourseList = studentCourseList;
	}

}
