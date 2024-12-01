package com.java.project.model;

import java.io.InputStream;

import javax.servlet.http.Part;

public class Crud {
    private int id;
    private String name;
    private String infor;
    private String address;
    private byte[] photo;
	public Crud() {
		super();
	}
	public Crud(int id, String name, String infor, String address, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.infor = infor;
		this.address = address;
		this.photo = photo;
	}
	
	public Crud(String name, String infor, String address, InputStream inputStream) {
		// TODO Auto-generated constructor stub
	}
	public Crud(int id, String name, String infor, String address, InputStream inputStream) {
		// TODO Auto-generated constructor stub
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
	public String getInfor() {
		return infor;
	}
	public void setInfor(String infor) {
		this.infor = infor;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
    
    
    
}
