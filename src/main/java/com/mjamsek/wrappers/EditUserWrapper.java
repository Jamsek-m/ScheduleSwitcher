package com.mjamsek.wrappers;

import java.util.List;

public class EditUserWrapper {
	
	private String username;
	private String displayName;
	private List<String> vloge;
	private long id;
	private String email;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public List<String> getVloge() {
		return vloge;
	}
	public void setVloge(List<String> roles) {
		this.vloge = roles;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
