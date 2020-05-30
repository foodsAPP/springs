package com.example.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.UsersException;
import com.example.demo.model.Response;
import com.example.demo.model.Users;
import com.example.demo.service.UsersService;




@RestController
@RequestMapping("/user")
public class UsersController {
	public static final Logger logger=
			LoggerFactory.getLogger(UsersController.class);
	@Autowired
      public UsersService service;
      
	//用户注册
	@PostMapping("/register")
	public Response<Users> register(@RequestBody Users u) {
		Response<Users> result = new Response<Users>();
		logger.debug("即将创建新用户频道"+u);
		try {
		Users saved=service.register(u);
		result.setStatus(Response.STATUS_OK);
		result.setData(saved);
		} catch (UsersException e) {
			logger.debug("用户已存在，不能注册",e);
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("用户已存在，不能注册");
		}
		return result;
	}
	//用户登录
	@GetMapping("/login/{username}/{password}")
	public Response<String> login(@PathVariable("username") String username,@PathVariable("password")String password) {
		Response<String> result =new Response<>();
		Users saved=service.login(username, password);
		if(saved!=null) {//登陆成功
			String uid=service.checkIn(username);
			//生成唯一编号    放入缓存
			result.setStatus(Response.STATUS_OK);
			result.setData(uid);
			result.setMessage("登录成功：");
		}else {
			logger.debug("密码错误");
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("密码错误");
		}
		return result;
	}
}
