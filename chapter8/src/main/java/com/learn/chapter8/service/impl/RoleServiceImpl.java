package com.learn.chapter8.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.learn.chapter8.dao.RoleDao;
import com.learn.chapter8.pojo.RoleBean;
import com.learn.chapter8.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public int insertRole(RoleBean role) {
		return roleDao.insertRole(role);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public int updateRole(RoleBean role) {
		return roleDao.updateRole(role);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@Override
	public int deleteRole(Integer id) {
		return roleDao.deleteRole(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS)
	@Override
	public RoleBean getRole(Integer id) {
		return roleDao.getRole(id);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.SUPPORTS)
	@Override
	public List<RoleBean> findRoles(String roleName, int start, int limit) {
		return roleDao.findRoles(roleName, new RowBounds(start, limit));
	}

}
