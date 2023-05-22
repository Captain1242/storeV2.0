package com.itjinling.store.service.impl;

import java.sql.SQLException;

import com.itjinling.store.dao.UserDao;
import com.itjinling.store.dao.impl.UserDaoImpl;
import com.itjinling.store.domain.User;
import com.itjinling.store.service.UserService;
import com.itjinling.store.utils.MailUtils;
import com.itjinling.store.utils.UUIDUtils;

/*
 * 用户模块的Service的实现类
 * */
public class UserServiceImpl implements UserService {

	@Override
	public User findByUsername(String username) throws SQLException{
		UserDao userDao = new UserDaoImpl();
		return userDao.findByUsername(username);
	}

	@Override
	public void save(User user) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		user.setUid(UUIDUtils.getUUID());
		user.setState(1);// 1代表未激活, 2:代表已经激活.
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		
		// 发送激活邮件:
		MailUtils.sendMail(user.getEmail(), code);
	}

	@Override
	public User findByCode(String code) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		return userDao.findByCode(code);
	}
	
	@Override
	public void update(User existUser) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		userDao.update(existUser);
	}
	
	@Override
	public User login(User user) throws SQLException {
		UserDao userDao = new UserDaoImpl();
		return userDao.login(user);
	}
}
