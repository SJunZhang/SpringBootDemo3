package org.springboot.sample.controller;

import java.util.List;

import org.springboot.sample.entity.User;
import org.springboot.sample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="user")
public class UserUI{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/listUser")
	public List<User> listUser(){
		return userService.getUser();
	}

}
