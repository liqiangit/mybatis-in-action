package com.learn.chapter8.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learn.chapter8.pojo.RoleBean;
import com.learn.chapter8.service.RoleService;

@Controller
public class RoleController {

	private static Logger log = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService = null;

	@RequestMapping("/role/getRole")
	@ResponseBody
	public RoleBean getRole(@RequestParam("id") int id) {
		RoleBean role = roleService.getRole(id);
		return role;
	}
}
