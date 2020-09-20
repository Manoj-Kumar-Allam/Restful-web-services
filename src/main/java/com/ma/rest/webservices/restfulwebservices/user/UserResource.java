package com.ma.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	private MessageSource messageSource;

	private final UserDAOService userDAOService;

	public UserResource(UserDAOService userDAOService, MessageSource messageSource) {
		super();
		this.userDAOService = userDAOService;
		this.messageSource = messageSource;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return this.userDAOService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = this.userDAOService.findOne(id);
		if (user == null) {
			throw new UserNotFoundException("Id - " + id);
		}

		EntityModel<User> resource = EntityModel.of(user);

		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

		resource.add(linkTo.withRel("all-users"));

		return resource;
	}

	@PostMapping("/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		this.userDAOService.save(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping("/users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		User deleteUser = this.userDAOService.deleteById(id);
		if (deleteUser == null) {
			throw new UserNotFoundException("Id - " + id);
		} else {
			return ResponseEntity.ok().build();
		}
	}

	// public String
	// helloWorldInternationalized(@RequestHeader(name="Accept-Language",
	// required=false) Locale locale)
	// As we using AcceptHeader local resolver we don't need to configure Locale.
	@GetMapping(path = "/hello-world-intern")
	public String helloWorldInternationalized() {
		return this.messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
	}

	@GetMapping("/users/{id}/posts")
	public List<Post> retrievePostsByUserId(@PathVariable int id) {
		return this.userDAOService.findAllPostsByUserId(id);
	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> savePostByUserId(@PathVariable int id, @RequestBody Post post) {
		Post newPost = this.userDAOService.createNewPost(id, post);
		//URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPost.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
