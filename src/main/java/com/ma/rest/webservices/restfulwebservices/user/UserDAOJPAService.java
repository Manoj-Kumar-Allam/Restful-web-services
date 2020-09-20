package com.ma.rest.webservices.restfulwebservices.user;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({ "jpa", "default" })
public class UserDAOJPAService implements UserDAOService {

	private final UserRepository userRepository;

	private final PostRepository postRepository;

	public UserDAOJPAService(UserRepository userRepository, PostRepository postRepository) {
		super();
		this.userRepository = userRepository;
		this.postRepository = postRepository;
	}

	@Override
	public List<User> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public User save(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User findOne(int id) {
		return this.userRepository.findById(Integer.valueOf(id)).orElse(null);
	}

	@Override
	public User deleteById(int id) {
		User deletedUser = this.userRepository.findById(Integer.valueOf(id)).orElse(null);
		this.userRepository.delete(deletedUser);
		return deletedUser;
	}

	@Override
	public List<Post> findAllPostsByUserId(int id) {
		User userById = this.userRepository.findById(Integer.valueOf(id)).orElse(null);
		if (userById != null) {
			return userById.getPosts();
		} else {
			throw new UserNotFoundException("Id - " + id);
		}
	}

	@Override
	public Post createNewPost(int id, Post post) {
		User userById = this.userRepository.findById(Integer.valueOf(id)).orElse(null);
		if (userById != null) {
			post.setUser(userById);
			return this.postRepository.save(post);
		} else {
			throw new UserNotFoundException("Id - " + id);
		}
	}

}
