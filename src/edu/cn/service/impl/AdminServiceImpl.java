package edu.cn.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.cn.dao.BaseDao;
import edu.cn.model.Course;
import edu.cn.model.User;
import edu.cn.service.AdminService;
import edu.cn.service.UserService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Resource
	private UserService userService;

	@Resource
	private BaseDao<User> userDao;

	@Resource
	private BaseDao<Course> courseDao;

	@Override
	public List<User> getStudents() {
		// TODO Auto-generated method stub
		return userDao.find("from User where type = 2");
	}

	@Override
	public List<User> getTeachers() {
		// TODO Auto-generated method stub
		return userDao.find("from User where type = 1");
	}

	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return courseDao.find("from Course");
	}

	@Override
	public List<User> getCourseTeachers(int courseId) {
		// TODO Auto-generated method stub
		Course course=courseDao.get(Course.class, courseId);
		return filter(course.getTeacherList());
		
	}

	@Override
	public void addStudent(User user) {
		// TODO Auto-generated method stub
		user.setType(2);
		userDao.save(user);
	}

	@Override
	public void addTeacher(User user) {
		// TODO Auto-generated method stub
		user.setType(1);
		userDao.save(user);

	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.save(course);
	}

	@Override
	public void addTeacherToCourse(int teacherId, int courseId) {
		// TODO Auto-generated method stub
		Course course=courseDao.get(Course.class, courseId);
		User teacher=userDao.get(User.class, teacherId);
		teacher.getTeacherCourseList().add(course);
		userDao.update(teacher);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		User temp = userDao.get(User.class, user.getId());
		temp.setUsername(user.getUsername());
		temp.setPassword(user.getPassword());
		temp.setName(user.getName());
		temp.setSex(user.getSex());
		temp.setDepartment(user.getDepartment());
		temp.setPhone(user.getPhone());
		temp.setEmail(user.getEmail());
		userDao.update(temp);
	}

	@Override
	public void updateCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.update(course);
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		User user = userDao.get(User.class, id);
		userDao.delete(user);

	}

	@Override
	public void deleteCourse(int id) {
		// TODO Auto-generated method stub
		Course course = courseDao.get(Course.class, id);
		for (User user : course.getTeacherList()) {
			deleteTeacherFromCourse(user.getId(), id);
		}
		for (User user : course.getStudentList()) {
			deleteStudentFromCourse(user.getId(), id);
		}
		Course temp=courseDao.get(Course.class, id);
		temp.setStudentList(null);
		temp.setTeacherList(null);
		courseDao.delete(temp);
	}

	@Override
	public void deleteTeacherFromCourse(int teacherId, int courseId) {
		// TODO Auto-generated method stub
		Course course=courseDao.get(Course.class, courseId);
		User teacher=userDao.get(User.class,teacherId);
		teacher.getTeacherCourseList().remove(course);
		userDao.update(teacher);
	}
	
	private void deleteStudentFromCourse(int studentId,int courseId){
		Course course=courseDao.get(Course.class, courseId);
		User student=userDao.get(User.class, studentId);
		student.getStudentCourseList().remove(course);
		userDao.update(student);
	}
	
	private List<User> filter(List<User> userList){
		List<User> list=new ArrayList<User>();
		for (User user : userList) {
			boolean ifExists=false;
			for (User u : list) {
				if(user.getId()==u.getId()){
					ifExists=true;
				}
			}
			if(!ifExists){
				list.add(user);
			}
		}
		return list;
	}

}
