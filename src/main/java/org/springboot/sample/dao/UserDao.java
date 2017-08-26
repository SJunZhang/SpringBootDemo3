package org.springboot.sample.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springboot.sample.entity.User;

@Mapper
public interface UserDao {
	List<User> getUser();
}
