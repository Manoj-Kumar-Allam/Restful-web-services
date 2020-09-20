package com.ma.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({ "local" })
public class UserDAOLocalService implements UserDAOService {

	private static List<User> users = new ArrayList<>();

	private static int userCount = 3;

	static {
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Eve", new Date()));
		users.add(new User(3, "Jack", new Date()));
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	@Override
	public User findOne(int id) {
		return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
	}

	@Override
	public User deleteById(int id) {
		Iterator<User> iterator = users.iterator();
		while (iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}

	@Override
	public List<Post> findAllPostsByUserId(int id) {
		User userById = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
		if (userById != null) {
			return userById.getPosts();
		} else {
			throw new UserNotFoundException("Id - " + id);
		}
	}

	@Override
	public Post createNewPost(int id, Post post) {
		User userById = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
		if (userById != null) {
			users.get(id).getPosts().add(post);
			return post;
		} else {
			throw new UserNotFoundException("Id - " + id);
		}
	}
}
