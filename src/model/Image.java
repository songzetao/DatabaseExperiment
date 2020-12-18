package model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Image {
	private int no;
	private String title;
	private String description;
	@JSONField(name = "src")
	private String path;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp;

	public Image() {

	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "{\"title\": \"" + title + "\", \"description\": \"" + description + "\", \"path\": \"" + path + "\"}";
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
