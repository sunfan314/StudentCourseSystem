package edu.cn.service;

import edu.cn.model.User;

public interface UserService {

	/**
	 * @param user
	 * @return	根据用户名和密码查找用户
	 */
	public User findUserByNameAndPassword(User user);

}
