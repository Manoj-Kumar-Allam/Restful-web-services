package com.ma.rest.webservices.restfulwebservices.user;

import java.util.List;

public interface UserDAOService {
	
	 List<User> findAll();
	 
	 User save(User user);
	 
	 User findOne(int id);
	 
	 User deleteById(int id);
	 
	 List<Post> findAllPostsByUserId(int id);
	 
	 Post createNewPost(int id, Post post);
}
