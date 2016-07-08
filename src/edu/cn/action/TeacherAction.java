package edu.cn.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import edu.cn.model.Property;

import com.opensymphony.xwork2.Action;

import edu.cn.model.Course;
import edu.cn.model.CourseStudent;
import edu.cn.model.User;
import edu.cn.service.AdminService;
import edu.cn.service.TeacherService;

public class TeacherAction implements Action {
	@Resource
	private TeacherService teacherService;

	@Resource
	private AdminService adminService;

	private int id;

	private String data;

	private User user;

	private JSONObject json;

	private String oldPassword;

	private String newPassword;

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
	 * @return	获取教师课程
	 * @throws Exception
	 */
	public String getTeacherCourses() throws Exception {
		List<Course> courseList = teacherService.getTeacherCourses(id);
		for (Course course : courseList) {
			course.setStudentNum(course.getStudentList().size());
			course.setStudentList(null);
			course.setTeacherList(null);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", courseList);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	获取课程学生成绩列表
	 * @throws Exception
	 */
	public String getCourseStudentScores() throws Exception {
		List<CourseStudent> list = teacherService.getCourseStudentScores(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	获取教师个人信息（属性格式）
	 * @throws Exception
	 */
	public String getTeacherInfo() throws Exception {
		List<Property> list = new ArrayList<Property>();
		String group1 = "账号信息";
		String group2 = "个人信息";
		String group3 = "联系方式";
		String editor = "can not be edited";
		User user = teacherService.getTeacherInfo(id);
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
	 * @return	获取教师信息（以json的形式）
	 * @throws Exception
	 */
	public String getJsonTeacherInfo() throws Exception {
		User user = teacherService.getTeacherInfo(id);
		user.setStudentCourseList(null);
		user.setTeacherCourseList(null);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", user);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	更新学生课程成绩
	 * @throws Exception
	 */
	public String updateStudentCourseScores() throws Exception {
		JSONArray array = JSONArray.fromObject(data);
		List<CourseStudent> list = new ArrayList<CourseStudent>();
		for (Object object : array) {
			JSONObject json = (JSONObject) object;
			CourseStudent cs = (CourseStudent) JSONObject.toBean(json,
					CourseStudent.class);
			list.add(cs);
			teacherService.updateStudentCourseScores(list);
		}
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	更新学生个人信息
	 * @throws Exception
	 */
	public String updateTeacherInfo() throws Exception {
		adminService.updateUser(user);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}

	/**
	 * @return	更新用户密码
	 * @throws Exception
	 */
	public String updatePassword() throws Exception {
		User user = teacherService.getTeacherInfo(id);
		java.util.Map<String, Object> map = new HashMap<String, Object>();
		if (oldPassword.equals(user.getPassword())) {
			teacherService.updatePassword(newPassword, id);
			map.put("success", true);
		} else {
			map.put("success", false);
		}
		json = JSONObject.fromObject(map);
		return SUCCESS;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


}
