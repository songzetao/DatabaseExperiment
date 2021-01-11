package model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Comment {
	private int imageID;
	private String username;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp;
	private String comment;
	public int getImageID() {
		return imageID;
	}
	public void setImageID(int imageID) {
		this.imageID = imageID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Comment() {
		// TODO 自动生成的构造函数存根
	}

}
