package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 菜谱模型类
 * @author ASUS
 *
 */
public class Cookbook implements Serializable {
	
	private static final long serialVersionUID = 8113986652205866086L;
	
	@Id
	private String id;
	private String title;
	private String cover; //菜谱封面
	private String maked; //菜谱被做过次数
	private int cstar ; //菜谱被点赞数
	private List<Comment> comments; //菜谱评论
	private List<Content> contents; //菜谱主体内容（原料+步骤）
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime dt = LocalDateTime.now(); //菜谱上传时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getMaked() {
		return maked;
	}
	public void setMaked(String maked) {
		this.maked = maked;
	}
	public int getCstar() {
		return cstar;
	}
	public void setCstar(int cstar) {
		this.cstar = cstar;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Content> getContents() {
		return contents;
	}
	public void setContents(List<Content> contents) {
		this.contents = contents;
	}
	public LocalDateTime getDt() {
		return dt;
	}
	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comments == null) ? 0 : comments.hashCode());
		result = prime * result + ((contents == null) ? 0 : contents.hashCode());
		result = prime * result + ((cover == null) ? 0 : cover.hashCode());
		result = prime * result + cstar;
		result = prime * result + ((dt == null) ? 0 : dt.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maked == null) ? 0 : maked.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cookbook other = (Cookbook) obj;
		if (comments == null) {
			if (other.comments != null)
				return false;
		} else if (!comments.equals(other.comments))
			return false;
		if (contents == null) {
			if (other.contents != null)
				return false;
		} else if (!contents.equals(other.contents))
			return false;
		if (cover == null) {
			if (other.cover != null)
				return false;
		} else if (!cover.equals(other.cover))
			return false;
		if (cstar != other.cstar)
			return false;
		if (dt == null) {
			if (other.dt != null)
				return false;
		} else if (!dt.equals(other.dt))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maked == null) {
			if (other.maked != null)
				return false;
		} else if (!maked.equals(other.maked))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Cookbook [id=" + id + ", title=" + title + ", cover=" + cover + ", maked=" + maked + ", cstar=" + cstar
				+ ", comments=" + comments + ", contents=" + contents + ", dt=" + dt + "]";
	}
	
	

}
