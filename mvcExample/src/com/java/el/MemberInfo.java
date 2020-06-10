package com.java.el;

public class MemberInfo {
	private String name;
	private String id;
	private String pwd;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
		System.out.println(toString());
	}
	@Override
	public String toString() {
		return "MemberInfo [name=" + name + ", id=" + id + ", pwd=" + pwd + "]";
	}
	
	
}
