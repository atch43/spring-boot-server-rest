package it.project.server.model;

public class Privilege {
	
	private Integer id;
	
	private Integer level;

	private String description;

	public Privilege() {
		
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Privilege [id=" + id + ", level=" + level + ", description=" + description + "]";
	}

}
