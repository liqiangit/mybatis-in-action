package com.learn.chapter8.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learn.chapter8.dao.UserDao;
import com.learn.chapter8.pojo.UserBean;
import com.learn.chapter8.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS)
	@Override
	public UserBean getUser(Integer id) {
		return userDao.getUser(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public int insertUser(UserBean user) {
		return userDao.insertUser(user);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public int deleteUser(Integer id) {
		return deleteUser(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public int updateUser(UserBean user) {
		return updateUser(user);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS)
	@Override
	public List<UserBean> findUsers(String userName, int start, int limit) {
		return userDao.findUsers(userName, new RowBounds(start, limit));
	}

}
