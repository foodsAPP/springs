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
import org.springframework.web.bind.annotation.RequestParam;
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
	//查看菜谱首页所有菜谱
	@GetMapping
	public List<Cookbook> getAllcookbook() {
		logger.info("正在返回打印全部信息");
		List<Cookbook>  books= service.getAllCookbook();
			return books;
		}
//	获取一个指定菜谱
	@GetMapping("/{id}")
	public  Cookbook  getCookbook(@PathVariable String id) {
		Cookbook c=service.getCookbook(id);
		logger.info("正在打印"+id+"信息：");
		if(c!=null) {
			return c;
		}else {
			logger.error("找不到指定菜谱");
			return null;
		}
		
	}
//	删除一个指定菜谱
	@DeleteMapping("/{id}")
 public ResponseEntity<String> deleteCookbook(@PathVariable String id){
		System.out.println("即将删除指定菜谱,id="+id);
		boolean result=service.deleteCookbook(id);
		if(result) {
			return ResponseEntity.ok().body("删除成功");
		}else {
			return ResponseEntity.ok().body("删除失败");
		}
	 
 }
//	新建一个菜谱
	@PostMapping
	public Cookbook createCookbook(@RequestBody Cookbook c) {
		System.out.println("即将创建菜谱"+c);
		Cookbook saved=service.createCookbook(c);
		return saved;
	}
	//更新菜谱
	@PutMapping
	public Cookbook updateCookbook(@RequestBody Cookbook c) {
		System.out.println("即将更新菜谱"+c);
		Cookbook update=service.updateCookbook(c);
		return update;
	}
	//通过菜谱标题获取菜谱(只支持英文标题)
	@GetMapping("/t/{title}")
	public List<Cookbook> searchTitle(@PathVariable String title) {
		System.out.println("即将寻找标题为："+title+" 的菜谱（英文）");
		List<Cookbook> t=service.searchTitle(title);
		return t;
	}
	//通过菜谱标题获取菜谱(中英文标题均支持)
	@GetMapping("/ct")
	public List<Cookbook> seachByTitle(@RequestParam String title) {
		System.out.println("即将寻找标题为："+title+" 的菜谱");
		return service.searchTitle(title);
	}
	//通过用户名查找菜谱
	@GetMapping("/c/{cover}")
	public List<Cookbook> searchCover(@PathVariable String cover) {
		System.out.println("即将寻找封面为"+cover+" 的菜谱");
		List<Cookbook> t=service.searchCover(cover);
		return t;
	}
	//
	@GetMapping("/hot")
	public List<Cookbook> gethotCookbooks() {
		return service.getLastLocalDateTimeCookbook();
	}
	
	//新增菜谱材料
		@PostMapping("/{cookbookId}/material")
		public Cookbook addMaterial(@PathVariable String cookbookId,@RequestBody Material material){
			Cookbook cmaterial = null;
			//把评论保存到数据库
	logger.debug("将为频道"+cookbookId+"新增一条评论"+material);
	return service.addMaterial(cookbookId, material);
		}
	
	//新增菜谱步骤
		@PostMapping("/{cookbookId}/step")
		public Cookbook addStep(@PathVariable String cookbookId,@RequestBody Step step){
			Cookbook cstep = null;
			//把评论保存到数据库
	logger.debug("将为频道"+cookbookId+"新增一条评论"+step);
	return service.addStep(cookbookId, step);
		}
	//新增菜谱评论
	@PostMapping("/{cookbookId}/comment")
	public Cookbook addComment(@PathVariable String cookbookId,@RequestBody Comment comment){
		Cookbook result = null;
		//把评论保存到数据库
logger.debug("将为频道"+cookbookId+"新增一条评论"+comment);
return service.addComment(cookbookId, comment);
	}
	//获取菜谱的热门评论
	@GetMapping("/{cookbookId}/hotcomments")
	public List<Comment> hotComments(@PathVariable String cookbookId){
		logger.debug("获取热门评论降为频道"+cookbookId);
		List<Comment> hotcomment=service.hotComments(cookbookId);
		return hotcomment;
//		return service.hotComments();
	}
	
}