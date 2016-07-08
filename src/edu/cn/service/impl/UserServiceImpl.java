package edu.cn.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.cn.dao.BaseDao;
import edu.cn.model.User;
import edu.cn.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Resource
	private BaseDao<User> userDao;

	@Override
	public User findUserByNameAndPassword(User user) {
		// TODO Auto-generated method stub
		List<Object> params=new ArrayList<Object>();
		params.add(user.getUsername());
		params.add(user.getPassword());
		return userDao.get("from User where username = ? and password = ?", params);
	}

}
