package edu.cn.service;

import java.util.List;

import edu.cn.model.Course;
import edu.cn.model.User;

public interface AdminService {
	
	/**
	 * @return	获取学生列表
	 */
	public List<User> getStudents();
	
	/**
	 * @return	获取教师列表
	 */
	public List<User> getTeachers();
	
	/**
	 * @return	获取课程列表
	 */
	public List<Course> getCourses();
	
	/**
	 * @param courseId
	 * @return	获取课程教师
	 */
	public List<User> getCourseTeachers(int courseId);

	/**
	 * @param user
	 * 	添加学生
	 */
	public void addStudent(User user);
	
	/**
	 * @param user
	 *	添加教师
	 */
	public void addTeacher(User user);
	
	/**
	 * @param course
	 * 	添加课程
	 */
	public void addCourse(Course course);
	
	/**
	 * @param teacherId
	 * @param courseId
	 * 添加课程教师
	 */
	public void addTeacherToCourse(int teacherId,int courseId);
	
	/**
	 * @param user
	 * 更新用户
	 */
	public void updateUser(User user);
	
	/**
	 * @param course
	 * 更新课程
	 */
	public void updateCourse(Course course);
	
	/**
	 * @param id
	 * 删除用户
	 */
	public void deleteUser(int id);
	
	/**
	 * @param id
	 * 删除课程
	 */
	public void deleteCourse(int id);
	
	/**
	 * @param teacherId
	 * @param courseId
	 * 删除课程教师
	 */
	public void deleteTeacherFromCourse(int teacherId,int courseId);
	

}
