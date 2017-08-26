package org.springboot.sample.service.impl;

import java.util.List;

import org.springboot.sample.dao.UserDao;
import org.springboot.sample.entity.User;
import org.springboot.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="UserService")
public class UserServiceImple implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getUser() {
		return userDao.getUser();
	}

}
