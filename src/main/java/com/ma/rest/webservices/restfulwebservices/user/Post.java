package com.ma.rest.webservices.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name="posts")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String description;
	
	@ManyToOne
	private User user;

	public Post() {
		super();
	}

	public Post(Integer id, String description, User user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

}
