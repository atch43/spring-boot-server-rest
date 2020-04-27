package it.project.server.pojo;


public class Skill {

	private Integer id;

	private String skill;
	
	private Double value;

	@Override
	public String toString() {
		return "Skill [id=" + id + ", skill=" + skill + ", value=" + value + ", getId()=" + getId() + ", getSkill()="
				+ getSkill() + ", getValue()=" + getValue() + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}