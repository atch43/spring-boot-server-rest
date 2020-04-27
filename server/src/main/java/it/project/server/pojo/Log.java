package it.project.server.pojo;


public class Log {

	private Integer id;

	private String message;
	
	private Long createdAt;
	
	private String extra;

	public Log(String message, String extra, Long createdAt) {
		this.message = message;
		this.extra = extra;
		this.createdAt = createdAt;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", message=" + message + ", createdAt=" + createdAt + ", extra=" + extra + ", getId()="
				+ getId() + ", getMessage()=" + getMessage() + ", getCreatedAt()=" + getCreatedAt() + ", getExtra()="
				+ getExtra() + "]";
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
	
	
	 
}