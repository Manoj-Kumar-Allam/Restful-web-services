package com.ma.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="Info about user")
@Entity(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min=2, message="Name should have atleast 2 characters")
	@NotBlank(message="Name can not be blank")
	@ApiModelProperty(notes="Name should have atleast 2 characters")
	private String name;
	
	@Past
	@ApiModelProperty(notes="Birth date should be in the past")
	private Date birthDate;
	
	@OneToMany(cascade= CascadeType.ALL, mappedBy="user")
	private List<Post> posts = new ArrayList<>();

	protected User() {
	}

	public User(Integer id, String name, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
	
	
}
