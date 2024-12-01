package com.java.project.model;

public class User {
	private int id;
	private String name;
	private String avatar;
	private String address;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(int id, String name, String avatar, String address, String email) {
		super();
		this.id = id;
		this.name = name;
		this.avatar = avatar;
		this.address = address;
		this.email = email;
	}
	
	public User( String name, String avatar, String address, String email) {
		super();
		this.name = name;
		this.avatar = avatar;
		this.address = address;
		this.email = email;
	}

}
