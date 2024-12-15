package com.java.project.model;

import java.io.InputStream;

import javax.servlet.http.Part;

public class Users {
    private int id;
    private String name;
    private String email;
    private String address;
    private String avatar;
    private String uploadPath;
    private String base64Image;
    
    
	public Users(String uploadPath,String name, String email, String address,  String avatar) {
		super();
	}
	public String getBase64Image() {
        return base64Image;
    }
 
    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

	public Users(int id, String name, String email, String address,  String  avatar) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.address = address;
		this.avatar = avatar;
		
	}


	public Users(String name, String email, String address, String avatar) {
		 this.name = name;
		    this.email = email;
		    this.address = address;
		    this.avatar = avatar;
	}


	public Users(String avatar) {
		this.avatar = avatar ;
	}


	public Users(int id, String avatar) {
		this.id = id ;
		this.avatar = avatar;
	}
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}




	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getAbsolutePath() {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean exists() {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean mkdirs() {
		// TODO Auto-generated method stub
		return false;
	}


















    

	}
    
    
    
	
	
