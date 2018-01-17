package com.learn.chapter2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.learn.chapter2.mapper.RoleMapper;
import com.learn.chapter2.po.Role;
import com.learn.chapter2.util.SqlSessionFactoryUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Chapter2Test {

	private static long uniqueId=20170930L;

	@Test
	public void test1Insert() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSession();

			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = new Role();
			role.setId(uniqueId);
			role.setRoleName("测试1");
			role.setNote("测试1");
			int result = roleMapper.insertRole(role);
			Assert.assertEquals(1, result);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Test
	public void test2Select() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSession();

			RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
			Role role = roleMapper.getRole(uniqueId);
			Assert.assertEquals("测试1",role.getNote());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

	@Test
	public void test3Select2() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSession();

			Role role = sqlSession.selectOne("com.learn.chapter2.mapper.RoleMapper.getRole", uniqueId);
			Assert.assertEquals("测试1",role.getNote());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void test5Select2() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSession();
			Map<String,Object> map=new HashMap<>(); 
			map.put("roleName","bob");
			List<Role> role = sqlSession.selectList("com.learn.chapter2.mapper.RoleMapper.getRoles",map);
			Assert.assertEquals(10,role.size());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	@Test
	public void test6Select2() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSession();
			Role role=new Role();
			role.setRoleName("bob");
			role.setCurrentPage(2);
//			role.setPageSize(10);
			List<Role> roles = sqlSession.selectList("com.learn.chapter2.mapper.RoleMapper.getRoles",role);
			Assert.assertEquals(10,roles.size());
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}	
	@Test
	public void test4Delete() {
		SqlSession sqlSession = null;
		try {
			sqlSession = SqlSessionFactoryUtil.openSession();

			int result = sqlSession.delete("com.learn.chapter2.mapper.RoleMapper.deleteRole", uniqueId);
			Assert.assertEquals(1, result);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}

}
