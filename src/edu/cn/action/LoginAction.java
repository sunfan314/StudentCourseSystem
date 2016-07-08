package edu.cn.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import edu.cn.model.User;
import edu.cn.service.UserService;

import com.opensymphony.xwork2.Action;

@Controller("loginAction")
@SuppressWarnings("all")
public class LoginAction implements Action {

	private int id;

	private User user;

	@Resource
	private UserService userService;

	/**
	 * @return
	 * @throws Exception
	 * @usage 用户登录
	 */
	/**
	 * @return	用户登录
	 * @throws Exception
	 */
	@Override
	public String execute() throws Exception {
		User u = userService.findUserByNameAndPassword(user);
		try {
			user.setId(u.getId());
			return String.valueOf(u.getType());
		} catch (NullPointerException e) {
			// TODO: handle exception
			return ERROR;
		}
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

}
