package com.manick.rest.webservices.restfulservices.resource;

import java.net.URI;
import java.util.List;

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

import com.manick.rest.webservices.restfulservices.entity.User;
import com.manick.rest.webservices.restfulservices.exception.UserNotFoundException;
import com.manick.rest.webservices.restfulservices.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserService userService;
	
	public UserResource(final UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path="/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping(path = "/users/{userId}")
	public User findUser(@PathVariable Long userId) {
		User user = userService.getOneUser(userId);
		if(user == null)
			throw new UserNotFoundException("ID: "+userId);
		return user;
	}
	
	@PostMapping(path="/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		// improve the code with returning the Response URI
		User savedUser = userService.saveUser(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/users/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userService.delete(userId);
	}
	
	@GetMapping(path = "/hateoas/users/{userId}")
	public EntityModel<User> retrieveUser(@PathVariable Long userId) {
		User user = userService.getOneUser(userId);
		if(user == null)
			throw new UserNotFoundException("ID: "+userId);

		// HATEOAS
		EntityModel<User> entityModel = EntityModel.of(user);
		
		//get the user by id and give a link to access all users in the same controller
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
}
