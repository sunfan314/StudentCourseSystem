package edu.cn.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

import edu.cn.model.Course;
import edu.cn.model.User;
import edu.cn.service.AdminService;

/**
 * @author sunfan314
 */
/**
 * @author sunfan314
 */
@Component("adminAction")
@SuppressWarnings("all")
public class AdminAction implements Action {
	@Resource
	private AdminService adminService;

	private int id;

	private User user;

	private Course course;

	private User teacherToAdd;

	private User teacherToRemove;

	private JSONObject json;

	private JSONArray jsonArray;
	
	private String sss;

	/**
	 * @return 获取学生列表
	 * @throws Exception
	 */
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		this.setSss("sssss");
		System.out.println("ss");
		List<User> studentList = adminService.getStudents();
		for (User user : studentList) {
			user.setStudentCourseList(null);
			user.setTeacherCourseList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", studentList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	获取教师列表
	 * @throws Exception
	 */
	public String getTeachers() throws Exception {
		List<User> teacherList = adminService.getTeachers();
		for (User user : teacherList) {
			user.setStudentCourseList(null);
			user.setTeacherCourseList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", teacherList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return 	获取课列表
	 * @throws Exception
	 */
	public String getCourses() throws Exception {
		List<Course> courseList = adminService.getCourses();
		for (Course course : courseList) {
			course.setStudentNum(course.getStudentList().size());
			course.setCourseTeachers(parse(adminService
					.getCourseTeachers(course.getCourseId())));
			course.setStudentList(null);
			course.setTeacherList(null);
			
			
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	/**
	 * @return	获取课程教师
	 * @throws Exception
	 */
	public String getCourseTeachers() throws Exception {
		List<User> courseTeacherList = adminService.getCourseTeachers(id);
		for (User user : courseTeacherList) {
			user.setStudentCourseList(null);
			user.setTeacherCourseList(null);
		}
		jsonArray = JSONArray.fromObject(courseTeacherList);
		return SUCCESS;
	}

	/**
	 * @return	获取课程可选教师
	 * @throws Exception
	 */
	public String getOtherCourseTeachers() throws Exception {
		List<User> teacherList = adminService.getTeachers();
		List<User> courseTeacherList = adminService.getCourseTeachers(id);
		List<User> list=new ArrayList<User>();
		for (User user : teacherList) {
			boolean isCourseTeacher=false;
			for (User user2 : courseTeacherList) {
				if(user.getId()==user2.getId()){
					isCourseTeacher=true;
				}
			}
			if(!isCourseTeacher){
				list.add(user);
			}
		}
		for (User user : list) {
			user.setStudentCourseList(null);
			user.setTeacherCourseList(null);
		}
		jsonArray = JSONArray.fromObject(list);
		return SUCCESS;
	}

	/**
	 * @return	添加学生
	 * @throws Exception
	 */
	public String addStudent() throws Exception {
		adminService.addStudent(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	添加教师
	 * @throws Exception
	 */
	public String addTeacher() throws Exception {
		adminService.addTeacher(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	
	/**
	 * @return	添加课程
	 * @throws Exception
	 */
	public String addCourse() throws Exception {
		adminService.addCourse(course);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	为课程添加任课教师
	 * @throws Exception
	 */
	public String addTeacherToCourse() throws Exception {
		adminService.addTeacherToCourse(teacherToAdd.getId(),
				course.getCourseId());
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	更新用户信息
	 * @throws Exception
	 */
	public String updateUser() throws Exception {
		adminService.updateUser(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	更新课程信息
	 * @throws Exception
	 */
	public String updateCourse() throws Exception {
		adminService.updateCourse(course);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	删除用户
	 * @throws Exception
	 */
	public String deleteUser() throws Exception {
		adminService.deleteUser(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	删除课程
	 * @throws Exception
	 */
	public String deleteCourse() throws Exception {
		adminService.deleteCourse(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	删除课程教师
	 * @throws Exception
	 */
	public String deleteTeacherFromCourse() throws Exception {
		adminService.deleteTeacherFromCourse(teacherToRemove.getId(),
				course.getCourseId());
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @param userList
	 * @return	将用户列表转为用户名字符串
	 */
	private String parse(List<User> userList) {
		String temp = "";
		for (User user : userList) {
			temp = temp + user.getUsername() + ",";
		}
		if(!(temp.equals(""))){
			return temp.substring(0, temp.length()-1);
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

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public User getTeacherToAdd() {
		return teacherToAdd;
	}

	public void setTeacherToAdd(User teacherToAdd) {
		this.teacherToAdd = teacherToAdd;
	}

	public User getTeacherToRemove() {
		return teacherToRemove;
	}

	public void setTeacherToRemove(User teacherToRemove) {
		this.teacherToRemove = teacherToRemove;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public String getSss() {
		return sss;
	}

	public void setSss(String sss) {
		this.sss = sss;
	}
	
	

}
