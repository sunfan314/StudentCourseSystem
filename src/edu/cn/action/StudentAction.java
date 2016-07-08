package edu.cn.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.dao.BaseDao;
import edu.cn.model.Course;
import edu.cn.model.CourseStudent;
import edu.cn.model.Property;
import edu.cn.model.User;
import edu.cn.service.AdminService;
import edu.cn.service.StudentService;
import edu.cn.service.TeacherService;

public class StudentAction implements Action {
	@Resource
	private StudentService studentService;
	
	@Resource
	private AdminService adminService;

	private int id;

	private User user;

	private int studentId;

	private int courseId;
	
	private int creditGet;

	private JSONObject json;

	/**
	 * @return	控制界面跳转
	 * @throws Exception
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	/**
	 * @return	学生课程界面跳转
	 * @throws Exception
	 */
	public String studentCourses() throws Exception{
		int count=0;
		List<Course> courseList = studentService.getStudentCourses(id);
		for (Course course : courseList) {
			String score = studentService.getStudentCourseScore(
					course.getCourseId(), id);
			if(!score.equals("")){
				int temp = Integer.parseInt(score);
				if (temp > 59) {
					count+=course.getCredit();
				} 
			}
		}
		this.creditGet=count;
		return SUCCESS;
	}

	/**
	 * @return	获取学生课程列表
	 * @throws Exception
	 */
	public String getStudentCourses() throws Exception {
		List<Course> courseList = studentService.getStudentCourses(id);
		for (Course course : courseList) {
			course.setCourseTeachers(parse(adminService
					.getCourseTeachers(course.getCourseId())));
			String score = studentService.getStudentCourseScore(
					course.getCourseId(), id);
			if (score.equals("")) {
				course.setScore("尚未评分");
				course.setCreditGet(0);
			} else {
				course.setScore(score);
				int temp = Integer.parseInt(score);
				if (temp > 59) {
					course.setCreditGet(course.getCredit());
				} else {
					course.setCreditGet(0);
					;
				}
			}
			course.setStudentList(null);
			course.setTeacherList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	获取学生课程选课状态
	 * @throws Exception
	 */
	public String getCourseSelectStatus() throws Exception {
		List<Course> courseList = adminService.getCourses();
		List<Course> selectedCourses = new ArrayList<Course>();
		List<Course> unselectedCourses = new ArrayList<Course>();
		for (Course course : courseList) {
			course.setStudentNum(course.getStudentList().size());
			course.setCourseTeachers(parse(adminService
					.getCourseTeachers(course.getCourseId())));
			course.setStudentList(null);
			course.setTeacherList(null);
			if (studentService.isCourseChosen(course.getCourseId(), id)) {
				course.setIsChosen("已选课");
				selectedCourses.add(course);
			} else {
				course.setIsChosen("未选课");
				unselectedCourses.add(course);
			}
		}
		selectedCourses.addAll(unselectedCourses);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", selectedCourses);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	获取学生个人信息
	 * @throws Exception
	 */
	public String getStudentInfo() throws Exception {
		List<Property> list = new ArrayList<Property>();
		String group1 = "账号信息";
		String group2 = "个人信息";
		String group3 = "联系方式";
		String editor = "can not be edited";
		User user = studentService.getStudentInfo(id);
		list.add(new Property("用户Id", user.getId(), group1, editor));
		list.add(new Property("用户名", user.getUsername(), group1, editor));
		list.add(new Property("姓名", user.getName(), group2, editor));
		list.add(new Property("性别", user.getSex(), group2, editor));
		list.add(new Property("院系", user.getDepartment(), group2, editor));
		list.add(new Property("电话号码", user.getPhone(), group3, editor));
		list.add(new Property("邮箱", user.getEmail(), group3, editor));
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	获取课程教师信息
	 * @throws Exception
	 */
	public String getCourseTeachers() throws Exception{
		List<User> courseTeacherList=studentService.getCourseTeachers(id);
		for (User user : courseTeacherList) {
			user.setStudentCourseList(null);
			user.setTeacherCourseList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseTeacherList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	更新学生个人信息
	 * @throws Exception
	 */
	public String updateStudentInfo() throws Exception {
		adminService.updateUser(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	学生选课
	 * @throws Exception
	 */
	public String addStudentCourse() throws Exception{
		studentService.addStudentCourse(courseId, studentId);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	学生退课
	 * @throws Exception
	 */
	public String deleteStudentCourse() throws Exception{
		studentService.deleteStudentCourse(courseId, studentId);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @param userList
	 * @return	将用户列表转化为用户名字符串
	 */
	private String parse(List<User> userList) {
		String temp = "";
		for (User user : userList) {
			temp = temp + user.getUsername() + ",";
		}
		if (!(temp.equals(""))) {
			return temp.substring(0, temp.length() - 1);
		}
		return temp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
	public int getCreditGet() {
		return creditGet;
	}

	public void setCreditGet(int creditGet) {
		this.creditGet = creditGet;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

}
