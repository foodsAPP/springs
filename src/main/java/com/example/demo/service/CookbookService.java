package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CookbookRepository;
import com.example.demo.model.Comment;
import com.example.demo.model.Cookbook;
import com.example.demo.model.Material;
import com.example.demo.model.Step;

@Service
public class CookbookService {
	public static final Logger logger=
			LoggerFactory.getLogger(CookbookService.class);
	
	@Autowired
	private CookbookRepository repo;

	public List<Cookbook> getAllChannel() {
		logger.info("准备返回从数据库中正在返回打印全部信息：");
		 return repo.findAll();
	}
	@Cacheable("cookbooks")
	public Cookbook getChannel(String id) {
		logger.info("准备从数据库中正在返回打印全部信息："+id);
		Optional<Cookbook> result=repo.findById(id);
			   if (result.isPresent()) {
				   return result.get();
			   }else {
				   return null;
	}
	}
	public boolean deleteChannel(String id) {
		boolean result=true;
		repo.deleteById(id);
		 return result;
	}
	
	public Cookbook createCookbook(Cookbook c) {
		 return repo.save(c);
	}
	
	public Cookbook updateCookbook(Cookbook c) {
		Cookbook saved=getChannel(c.getId());
		if(saved != null) {
			if(c.getTitle() != null) {
				saved.setTitle(c.getTitle());
			}
			if(c.getMaked() != null) {
				saved.setMaked(c.getMaked());
			}
			if(c.getMaterial()!=null) {
				saved.getMaterial().addAll(c.getMaterial());
			}else {
				saved.setComments(c.getComments());
			}
			if(c.getStep()!=null) {
				saved.getStep().addAll(c.getStep());
			}else {
				saved.setComments(c.getComments());
			}
			if(c.getComments()!=null) {
				saved.getComments().addAll(c.getComments());
			}else {
				saved.setComments(c.getComments());
			}
			if(c.getCover()!=null) {
				saved.setCover(c.getCover());
			}
		}
		 return repo.save(saved);
	}
	//通过菜谱标题查询
	public List<Cookbook> searchTitle(String title) {
		return repo.findByTitle(title);
	}
	//通过菜谱封面查询
	public List<Cookbook> searchCover(String cover) {
		return repo.findByCover(cover);
	}
	public List<Cookbook> getLastLocalDateTimeChannel() {
		LocalDateTime now=LocalDateTime.now();
		LocalDateTime today=LocalDateTime.of(now.getYear(),now.getMonthValue(),now.getDayOfMonth(),0,0);
		return repo.findByCommentsDtAfter(today);
	}
	public List<Comment> hotComments(String channelId) {
		List<Comment> result=new ArrayList<>();
		Cookbook saved=getChannel(channelId);
	    if(saved != null) {
	    	saved.getComments().sort(new Comparator<Comment>() {

				@Override
				public int compare(Comment o1, Comment o2) {
					if(o1.getStar()==o2.getStar()) {
						return 0;
					}else if(o1.getStar()<o2.getStar()) {
						return 1;
					}else {
						return -1;
					}
				}		
	    	});
	    	if(saved.getComments().size()>3) {
	    		result=saved.getComments().subList(0,3);
	    	}else {
	    		result=saved.getComments();
	    	}
	    }
	    return result;
	}
	
	public Cookbook addComment(String channelId, Comment comment) {
		Cookbook saved=getChannel(channelId);
	    if(saved != null) {
	    	if(saved.getComments()==null) {
				saved.setComments(new ArrayList<>());
			}
	    	saved.getComments().add(comment);
	    	return repo.save(saved);
	    }
	    return null;
	}
	public Cookbook addMaterial(String channelId, Material material) {
		Cookbook saved=getChannel(channelId);
	    if(saved != null) {
	    	if(saved.getMaterial()==null) {
				saved.setMaterial(new ArrayList<>());
			}
	    	saved.getMaterial().add(material);
	    	return repo.save(saved);
	    }
	    return null;
	}
	public Cookbook addStep(String channelId, Step step) {
		Cookbook saved=getChannel(channelId);
	    if(saved != null) {
	    	if(saved.getStep()==null) {
				saved.setStep(new ArrayList<>());
			}
	    	saved.getStep().add(step);
	    	return repo.save(saved);
	    }
	    return null;
	}

	
	
}