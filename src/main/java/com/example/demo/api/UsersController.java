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
      
	//�û�ע��
	@PostMapping("/register")
	public Response<Users> register(@RequestBody Users u) {
		Response<Users> result = new Response<Users>();
		logger.debug("�����������û�Ƶ��"+u);
		try {
		Users saved=service.register(u);
		result.setStatus(Response.STATUS_OK);
		result.setData(saved);
		} catch (UsersException e) {
			logger.debug("�û��Ѵ��ڣ�����ע��",e);
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("�û��Ѵ��ڣ�����ע��");
		}
		return result;
	}
	//�û���¼
	@GetMapping("/login/{username}/{password}")
	public Response<String> login(@PathVariable("username") String username,@PathVariable("password")String password) {
		Response<String> result =new Response<>();
		Users saved=service.login(username, password);
		if(saved!=null) {//��½�ɹ�
			String uid=service.checkIn(username);
			//����Ψһ���    ���뻺��
			result.setStatus(Response.STATUS_OK);
			result.setData(uid);
			result.setMessage("��¼�ɹ���");
		}else {
			logger.debug("�������");
			result.setStatus(Response.STATUS_ERROR);
			result.setMessage("�������");
		}
		return result;
	}
}
