package com.jxau.model;


/**
 * 朋友圈类，里面有tilte、iocn、content三个内容 根据之前登陆的用户名username从数据库中找到对应的朋友圈内容
 * 比如username=张三，就去找张三的朋友圈内容。
 * 
 * @author 52109
 *
 */
public class UserMoment {

	public String username;
	public String mood;
	public String image;

	public UserMoment() {

	}
	
	public UserMoment(String username) {
		username = this.username;
	}

	public UserMoment(String username, String mood, String image) {
		username = this.username;
		mood = this.mood;
		image = this.image;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public String getMoods() {
		return "{\"headImg\":\"" +this.getImage() + "\",\"username\":\""
				+ this.getUsername() + "\",\"mood\":\"" + this.getMood() + "\"}";
		
	}
	
	
	
	

	

}
