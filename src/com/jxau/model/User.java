package com.jxau.model;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private String image;
	

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	//用户自己的朋友圈
	private UserMoment userMoment;
	
	public UserMoment getUserMoment() {
		return userMoment;
	}
	public void setUserMoment(UserMoment userMoment) {
		this.userMoment = userMoment;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	
	
}
