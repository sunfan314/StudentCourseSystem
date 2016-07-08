package edu.cn.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.cn.dao.BaseDao;
import edu.cn.model.Course;
import edu.cn.model.CourseStudent;
import edu.cn.model.CourseStudentPK;
import edu.cn.model.User;
import edu.cn.service.AdminService;
import edu.cn.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Resource
	private AdminService adminService;

	@Resource
	private BaseDao<User> userDao;

	@Resource
	private BaseDao<Course> courseDao;

	@Resource
	private BaseDao<CourseStudent> courseStudentDao;

	@Override
	public User getStudentInfo(int id) {
		// TODO Auto-generated method stub
		return userDao.get(User.class, id);
	}

	@Override
	public List<Course> getStudentCourses(int id) {
		// TODO Auto-generated method stub
		User user = userDao.get(User.class, id);
		return user.getStudentCourseList();
	}

	@Override
	public String getStudentCourseScore(int courseId, int studentId) {
		// TODO Auto-generated method stub
		CourseStudent cs = courseStudentDao.get(CourseStudent.class,
				new CourseStudentPK(courseId, studentId));
		return cs.getScore();
	}

	@Override
	public List<User> getCourseTeachers(int id) {
		// TODO Auto-generated method stub
		Course course = courseDao.get(Course.class, id);
		return filter(course.getTeacherList());
	}

	@Override
	public boolean isCourseChosen(int courseId, int studentId) {
		// TODO Auto-generated method stub
		List<Object> params = new ArrayList<Object>();
		params.add(courseId);
		params.add(studentId);
		List<CourseStudent> list = courseStudentDao.find(
				"from CourseStudent where courseId = ? and studentId = ?",
				params);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void addStudentCourse(int courseId, int studentId) {
		// TODO Auto-generated method stub
		User user = userDao.get(User.class, studentId);
		Course course = courseDao.get(Course.class, courseId);
		user.getStudentCourseList().add(course);
		userDao.update(user);
	}

	@Override
	public void deleteStudentCourse(int courseId, int studentId) {
		// TODO Auto-generated method stub
		User user = userDao.get(User.class, studentId);
		Course course = courseDao.get(Course.class, courseId);
		user.getStudentCourseList().remove(course);
		userDao.update(user);

	}

	private List<User> filter(List<User> userList) {
		List<User> list = new ArrayList<User>();
		for (User user : userList) {
			boolean ifExists = false;
			for (User u : list) {
				if (user.getId() == u.getId()) {
					ifExists = true;
				}
			}
			if (!ifExists) {
				list.add(user);
			}
		}
		return list;
	}
}
