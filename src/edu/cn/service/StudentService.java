package edu.cn.service;

import java.util.List;

import edu.cn.model.Course;
import edu.cn.model.User;

public interface StudentService {

	/**
	 * @param id
	 * @return	获取学生个人信息
	 */
	public User getStudentInfo(int id);

	/**
	 * @param id
	 * @return	获取学生课程
	 */
	public List<Course> getStudentCourses(int id);
	
	/**
	 * @param courseId
	 * @param studentId
	 * @return	获取学生课程成绩
	 */
	public String getStudentCourseScore(int courseId,int studentId);
	
	/**
	 * @param id
	 * @return	获取课程教师
	 */
	public List<User> getCourseTeachers(int id);
	
	/**
	 * @param courseId
	 * @param studentId
	 * @return	判断学生是否选择某门课程
	 */
	public boolean isCourseChosen(int courseId,int studentId);

	/**
	 * @param courseId
	 * @param studentId
	 * 	学生选课
	 */
	public void addStudentCourse(int courseId, int studentId);

	/**
	 * @param courseId
	 * @param studentId
	 * 	学生退选课程
	 */
	public void deleteStudentCourse(int courseId, int studentId);

	
	
	
	

}
