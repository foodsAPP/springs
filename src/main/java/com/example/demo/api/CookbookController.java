package com.example.demo.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Comment;
import com.example.demo.model.Cookbook;
import com.example.demo.model.Material;
import com.example.demo.model.Step;
import com.example.demo.service.CookbookService;

@RestController
@RequestMapping("/cookbook")
public class CookbookController {
	public static final Logger logger=
			LoggerFactory.getLogger(CookbookController.class);
	@Autowired
	private CookbookService service;
//	@Autowired
//	private UsersService userservice;
	//�鿴������ҳ���в���
	@GetMapping
	public List<Cookbook> getAllcookbook() {
		logger.info("���ڷ��ش�ӡȫ����Ϣ");
		List<Cookbook>  books= service.getAllChannel();
			return books;
		}
//	��ȡһ��ָ��Ƶ��
	@GetMapping("/{id}")
	public  Cookbook  getChannel(@PathVariable String id) {
		Cookbook c=service.getChannel(id);
		logger.info("���ڴ�ӡ"+id+"��Ϣ��");
		if(c!=null) {
			return c;
		}else {
			logger.error("�Ҳ���ָ��Ƶ��");
			return null;
		}
		
	}
//	ɾ��һ��ָ��Ƶ��
	@DeleteMapping("/{id}")
 public ResponseEntity<String> deleteChannel(@PathVariable String id){
		System.out.println("����ɾ��ָ��Ƶ��,id="+id);
		boolean result=service.deleteChannel(id);
		if(result) {
			return ResponseEntity.ok().body("ɾ���ɹ�");
		}else {
			return ResponseEntity.ok().body("ɾ��ʧ��");
		}
	 
 }
//	�½�һ��Ƶ��
	@PostMapping
	public Cookbook createCookbook(@RequestBody Cookbook c) {
		System.out.println("��������Ƶ��"+c);
		Cookbook saved=service.createCookbook(c);
		return saved;
	}
	@PutMapping
	public Cookbook updateCookbook(@RequestBody Cookbook c) {
		System.out.println("��������Ƶ��"+c);
		Cookbook update=service.updateCookbook(c);
		return update;
	}
	//ͨ�����ױ����ȡ����
	@GetMapping("/s/{title}")
	public List<Cookbook> searchTitle(@PathVariable String title) {
		System.out.println("����Ѱ�ұ���"+title);
		List<Cookbook> t=service.searchTitle(title);
		return t;
	}
	//
	@GetMapping("/hot")
	public List<Cookbook> gethotChannel() {
		return service.getLastLocalDateTimeChannel();
	}
	
	//�������ײ���
		@PostMapping("/{channelId}/material")
		public Cookbook addMaterial(@PathVariable String channelId,@RequestBody Material material){
			Cookbook cmaterial = null;
			//�����۱��浽���ݿ�
	logger.debug("��ΪƵ��"+channelId+"����һ������"+material);
	return service.addMaterial(channelId, material);
		}
	
	//�������ײ���
		@PostMapping("/{channelId}/step")
		public Cookbook addStep(@PathVariable String channelId,@RequestBody Step step){
			Cookbook cstep = null;
			//�����۱��浽���ݿ�
	logger.debug("��ΪƵ��"+channelId+"����һ������"+step);
	return service.addStep(channelId, step);
		}
	//������������
	@PostMapping("/{channelId}/comment")
	public Cookbook addComment(@PathVariable String channelId,@RequestBody Comment comment){
		Cookbook result = null;
		//�����۱��浽���ݿ�
logger.debug("��ΪƵ��"+channelId+"����һ������"+comment);
return service.addComment(channelId, comment);
	}
	//��ȡ���׵���������
	@GetMapping("/{channelId}/hotcomments")
	public List<Comment> hotComments(@PathVariable String channelId){
		logger.debug("��ȡ�������۽�ΪƵ��"+channelId);
		List<Comment> hotcomment=service.hotComments(channelId);
		return hotcomment;
//		return service.hotComments();
	}
	
}
