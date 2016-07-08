package edu.cn.service;

import java.util.List;

import edu.cn.model.Course;
import edu.cn.model.CourseStudent;
import edu.cn.model.User;

public interface TeacherService {

	/**
	 * @param teacherId
	 * @return	获取教师课程
	 */
	public List<Course> getTeacherCourses(int teacherId);
	
	/**
	 * @param courseId
	 * @return	获取学生课程成绩信心
	 */
	public List<CourseStudent> getCourseStudentScores(int courseId);
	
	/**
	 * @param teacherId
	 * @return	获取教师信息
	 */
	public User getTeacherInfo(int teacherId);

	/**
	 * @param list
	 * 	更新学生课程成绩
	 */
	public void updateStudentCourseScores(List<CourseStudent> list);

	/**
	 * @param newPassword
	 * @param userId
	 * 	更新用户密码
	 */
	public void updatePassword(String newPassword,int userId);

	
}
