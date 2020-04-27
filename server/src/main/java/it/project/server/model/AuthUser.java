package it.project.server.model;

public class AuthUser {
	
	private String username;
	private String password;
	private String token;
	private Privilege privilege;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public AuthUser(String username, String token, Privilege privilege) {
		this.username = username;
		this.token = token;
		this.privilege = privilege;
	}
	
}
