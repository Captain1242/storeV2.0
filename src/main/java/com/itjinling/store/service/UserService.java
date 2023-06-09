package com.itjinling.store.service;

import java.sql.SQLException;

import com.itjinling.store.domain.User;

/*
 * 用户模块的Service的接口
 * */
public interface UserService {

	User findByUsername(String username) throws SQLException;

	void save(User user) throws SQLException;

	User findByCode(String code)throws SQLException;

	void update(User existUser)throws SQLException;

	User login(User user)throws SQLException;



}
