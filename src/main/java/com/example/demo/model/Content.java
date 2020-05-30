package com.example.demo.model;

import java.util.List;

/**
 * 菜谱主体内容模型类
 * @author ASUS
 *
 */
public class Content {
	private List<Material> materials; //菜谱原料
	private List<Step> steps; //菜谱步骤
	public List<Material> getMaterials() {
		return materials;
	}
	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materials == null) ? 0 : materials.hashCode());
		result = prime * result + ((steps == null) ? 0 : steps.hashCode());
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
		Content other = (Content) obj;
		if (materials == null) {
			if (other.materials != null)
				return false;
		} else if (!materials.equals(other.materials))
			return false;
		if (steps == null) {
			if (other.steps != null)
				return false;
		} else if (!steps.equals(other.steps))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Content [materials=" + materials + ", steps=" + steps + "]";
	}
	
	
}
