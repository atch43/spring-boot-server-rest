package it.project.server.pojo;

public class Address {
	
	private Integer id;
	
	private Integer userId;

	private String CAP;
	
	private String street;
	
	private String number;
	
	private Boolean residence;
	
	public Address() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCAP() {
		return CAP;
	}

	public void setCAP(String cAP) {
		CAP = cAP;
	}
 
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Boolean getResidence() {
		return residence;
	}

	public void setResidence(Boolean residence) {
		this.residence = residence;
	}

	
}
