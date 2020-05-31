package com.example.demo.model;

/**
 * 菜谱步骤模型类
 * @author ASUS
 *
 */
public class Step {
	private String stitle; //菜谱步骤标题
	private String scontent; //菜谱步骤内容
	public String getStitle() {
		return stitle;
	}
	public void setStitle(String stitle) {
		this.stitle = stitle;
	}
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((scontent == null) ? 0 : scontent.hashCode());
		result = prime * result + ((stitle == null) ? 0 : stitle.hashCode());
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
		Step other = (Step) obj;
		if (scontent == null) {
			if (other.scontent != null)
				return false;
		} else if (!scontent.equals(other.scontent))
			return false;
		if (stitle == null) {
			if (other.stitle != null)
				return false;
		} else if (!stitle.equals(other.stitle))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Step [stitle=" + stitle + ", scontent=" + scontent + "]";
	}
	
	
}