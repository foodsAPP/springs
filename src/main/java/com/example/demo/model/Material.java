package com.example.demo.model;

/**
 * 菜谱原料模型类
 * @author ASUS
 *
 */
public class Material {
	private String ingredients; //菜谱食材
	private String dosage; //菜谱食材用量
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dosage == null) ? 0 : dosage.hashCode());
		result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
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
		Material other = (Material) obj;
		if (dosage == null) {
			if (other.dosage != null)
				return false;
		} else if (!dosage.equals(other.dosage))
			return false;
		if (ingredients == null) {
			if (other.ingredients != null)
				return false;
		} else if (!ingredients.equals(other.ingredients))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Material [ingredients=" + ingredients + ", dosage=" + dosage + "]";
	}
	
	
}