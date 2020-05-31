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
	//查看菜谱首页所有菜谱
	@GetMapping
	public List<Cookbook> getAllcookbook() {
		logger.info("正在返回打印全部信息");
		List<Cookbook>  books= service.getAllChannel();
			return books;
		}
//	获取一个指定频道
	@GetMapping("/{id}")
	public  Cookbook  getChannel(@PathVariable String id) {
		Cookbook c=service.getChannel(id);
		logger.info("正在打印"+id+"信息：");
		if(c!=null) {
			return c;
		}else {
			logger.error("找不到指定频道");
			return null;
		}
		
	}
//	删除一个指定频道
	@DeleteMapping("/{id}")
 public ResponseEntity<String> deleteChannel(@PathVariable String id){
		System.out.println("即将删除指定频道,id="+id);
		boolean result=service.deleteChannel(id);
		if(result) {
			return ResponseEntity.ok().body("删除成功");
		}else {
			return ResponseEntity.ok().body("删除失败");
		}
	 
 }
//	新建一个频道
	@PostMapping
	public Cookbook createCookbook(@RequestBody Cookbook c) {
		System.out.println("即将创建频道"+c);
		Cookbook saved=service.createCookbook(c);
		return saved;
	}
	@PutMapping
	public Cookbook updateCookbook(@RequestBody Cookbook c) {
		System.out.println("即将更新频道"+c);
		Cookbook update=service.updateCookbook(c);
		return update;
	}
	//通过菜谱标题获取菜谱
	@GetMapping("/s/{title}")
	public List<Cookbook> searchTitle(@PathVariable String title) {
		System.out.println("即将寻找标题"+title);
		List<Cookbook> t=service.searchTitle(title);
		return t;
	}
	//
	@GetMapping("/hot")
	public List<Cookbook> gethotChannel() {
		return service.getLastLocalDateTimeChannel();
	}
	
	//新增菜谱材料
		@PostMapping("/{channelId}/material")
		public Cookbook addMaterial(@PathVariable String channelId,@RequestBody Material material){
			Cookbook cmaterial = null;
			//把评论保存到数据库
	logger.debug("将为频道"+channelId+"新增一条评论"+material);
	return service.addMaterial(channelId, material);
		}
	
	//新增菜谱步骤
		@PostMapping("/{channelId}/step")
		public Cookbook addStep(@PathVariable String channelId,@RequestBody Step step){
			Cookbook cstep = null;
			//把评论保存到数据库
	logger.debug("将为频道"+channelId+"新增一条评论"+step);
	return service.addStep(channelId, step);
		}
	//新增菜谱评论
	@PostMapping("/{channelId}/comment")
	public Cookbook addComment(@PathVariable String channelId,@RequestBody Comment comment){
		Cookbook result = null;
		//把评论保存到数据库
logger.debug("将为频道"+channelId+"新增一条评论"+comment);
return service.addComment(channelId, comment);
	}
	//获取菜谱的热门评论
	@GetMapping("/{channelId}/hotcomments")
	public List<Comment> hotComments(@PathVariable String channelId){
		logger.debug("获取热门评论降为频道"+channelId);
		List<Comment> hotcomment=service.hotComments(channelId);
		return hotcomment;
//		return service.hotComments();
	}
	
}
