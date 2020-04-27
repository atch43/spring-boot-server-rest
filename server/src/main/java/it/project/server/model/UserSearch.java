package it.project.server.model;

public class UserSearch {
	private String id;
	private String username;
	private String name;
	private String sortBy;
	private String sort;

	public UserSearch() {
		id = "";
		username = "";
		name = "";
		sortBy = "";
		sort = "asc";
	}

	public UserSearch(String id2, String username2, String nominativo2, String sortBy, String sort) {
		id = id2 != null ? id2 : "";
		username = username2 != null ? username2 : "";
		name = nominativo2 != null ? nominativo2 : "";
		this.sort = sort != null ? sort : "";

		if (sortBy != null) {
			if (sortBy.contentEquals("nominativo"))
				this.sortBy = "CONCAT(nome, ' ', cognome)";
			else
				this.sortBy = sortBy;
		}
		else {
			this.sortBy = "ID";
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	 
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}
}
