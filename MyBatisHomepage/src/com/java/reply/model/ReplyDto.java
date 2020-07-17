package com.java.reply.model;

public class ReplyDto {
	private int bunho;
	private String line_reply;
	private String user_ip;
	public int getBunho() {
		return bunho;
	}
	public String getUser_ip() {
		return user_ip;
	}
	public void setUser_ip(String user_ip) {
		this.user_ip = user_ip;
	}
	public void setBunho(int bunho) {
		this.bunho = bunho;
	}
	public String getLine_reply() {
		return line_reply;
	}
	public void setLine_reply(String line_reply) {
		this.line_reply = line_reply;
	}
	@Override
	public String toString() {
		return "ReplyDto [bunho=" + bunho + ", line_reply=" + line_reply + ", user_ip=" + user_ip + "]";
	}
	
}
