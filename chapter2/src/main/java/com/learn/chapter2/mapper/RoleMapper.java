package com.learn.chapter2.mapper;

import org.apache.ibatis.annotations.Select;

import com.learn.chapter2.po.Role;

public interface RoleMapper {

	int insertRole(Role role);

	Role getRole(Long id);

	int deleteRole(Long id);

	@Select(value = "select id, role_name as roleName, note from t_role where id = #{id}")
	Role getRole2(Long id);
}
