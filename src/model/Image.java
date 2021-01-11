package model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Image {
	private int id;
	private String title;
	private String description;
	private int hitsNumber;
	

	@JSONField(name = "src")
	private String path;
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp;

	public Image() {
		hitsNumber=0;
	}
	public int getHitsNumber() {
		return hitsNumber;
	}

	public void setHitsNumber(int hitsNumber) {
		this.hitsNumber = hitsNumber;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "{\"id\": \"" + id + "\", \"title\": \"" + title + "\", \"description\": \"" + description
				+ "\", \"path\": \"" + path + "\", \"timestamp\": \"" + timestamp + "\"}";
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
