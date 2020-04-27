package it.project.server.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import it.project.server.privileges.enums.Sex;

public class User implements Authentication {

	private static final long serialVersionUID = -4116165543244291306L;

	
	private Integer id;

	private String firstName;

	private String lastName;

	private String username;
	
	private String password;

	// readonly
	private Integer age;

	private Sex sex;

	private String telephone;

	private Privilege privilege;

	private Date dob; //to date

	private List<Address> addresses;

	private List<Skill> skills;

	private String token;

	 
 

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", age=" + age + ", sex=" + sex + ", telephone=" + telephone
				+ ", privilege=" + privilege + ", dob=" + dob.toString() + ", addresses=" + addresses + ", skills=" + skills
				+ ", token=" + token + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}


	@Override
	public String getName() {
		return this.firstName + " " + this.lastName;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	public Object getCredentials() {
		return username+":"+password;
	}

	@Override
	public Object getDetails() {
		return this.toString();
	}

	@Override
	public Object getPrincipal() {
		return this.username;
	}

	@Override
	public boolean isAuthenticated() {
		return SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if(isAuthenticated)
			SecurityContextHolder.getContext().setAuthentication(this);
		else
			SecurityContextHolder.getContext().setAuthentication(null);
	}

}
