package edu.cn.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.cn.dao.BaseDao;
import edu.cn.model.Course;
import edu.cn.model.CourseStudent;
import edu.cn.model.User;
import edu.cn.service.TeacherService;

@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Resource
	private BaseDao<User> userDao;
	
	@Resource
	private BaseDao<CourseStudent> courseStudentDao;
	
	@Override
	public List<Course> getTeacherCourses(int teacherId) {
		// TODO Auto-generated method stub
		List<Course> courseList = userDao.get(User.class, teacherId)
				.getTeacherCourseList();
		return courseList;
	}

	@Override
	public List<CourseStudent> getCourseStudentScores(int courseId) {
		// TODO Auto-generated method stub
		List<CourseStudent> list=courseStudentDao.find("from CourseStudent where courseId = ?",courseId);
		for (CourseStudent courseStudent : list) {
			User user=userDao.get(User.class,courseStudent.getStudentId());
			courseStudent.setUsername(user.getUsername());
			courseStudent.setStudentName(user.getName());
		}
		return list;
	}

	@Override
	public User getTeacherInfo(int teacherId) {
		// TODO Auto-generated method stub
		return userDao.get(User.class, teacherId);
	}

	@Override
	public void updateStudentCourseScores(List<CourseStudent> list) {
		// TODO Auto-generated method stub
		for (CourseStudent cs : list) {
			courseStudentDao.update(cs);
		}
	}

	@Override
	public void updatePassword(String newPassword,int userId) {
		// TODO Auto-generated method stub
		User user=userDao.get(User.class, userId);
		user.setPassword(newPassword);
		userDao.update(user);
	}

}
