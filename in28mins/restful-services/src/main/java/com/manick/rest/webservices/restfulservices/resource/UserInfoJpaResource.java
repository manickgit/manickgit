package com.manick.rest.webservices.restfulservices.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.manick.rest.webservices.restfulservices.controller.PostRepository;
import com.manick.rest.webservices.restfulservices.controller.UserInfoRepository;
import com.manick.rest.webservices.restfulservices.entity.Post;
import com.manick.rest.webservices.restfulservices.entity.UserInfo;
import com.manick.rest.webservices.restfulservices.exception.UserNotFoundException;

import jakarta.validation.Valid;

@RestController
public class UserInfoJpaResource {

	
	private UserInfoRepository userInfoRepository;
	
	private PostRepository postRepository;
	
	public UserInfoJpaResource(final UserInfoRepository userInfoRepository, PostRepository postRepository) {
		this.userInfoRepository = userInfoRepository;
		this.postRepository = postRepository;
	}
	
	@GetMapping(path="/jpa/ui/users")
	public List<UserInfo> getAllUsers() {
		return userInfoRepository.findAll();
	}
	
	@GetMapping(path = "/jpa/ui/users/{userId}")
	public UserInfo findUser(@PathVariable Long userId) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		if(userInfo.isEmpty())
			throw new UserNotFoundException("ID: "+userId);
		return userInfo.get();
	}
	
	@PostMapping(path="/jpa/ui/users")
	public ResponseEntity<UserInfo> createUser(@Valid @RequestBody UserInfo user) {
		// improve the code with returning the Response URI
		UserInfo savedUserInfo = userInfoRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUserInfo.getId())
			.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping(path="/jpa/ui/users/{userId}")
	public void deleteUser(@PathVariable Long userId) {
		userInfoRepository.deleteById(userId);
	}
	
	@GetMapping(path = "/jpa/ui/hateoas/users/{userId}")
	public EntityModel<UserInfo> retrieveUser(@PathVariable Long userId) {
		Optional<UserInfo> userInfo = userInfoRepository.findById(userId);
		if(userInfo == null)
			throw new UserNotFoundException("ID: "+userId);

		// HATEOAS
		EntityModel<UserInfo> entityModel = EntityModel.of(userInfo.get());
		
		//get the UserInfo by id and give a link to access all users in the same controller
		
		WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
		entityModel.add(link.withRel("all-users"));
		
		return entityModel;
	}
	
	@GetMapping(path="/jpa/ui/users/{userId}/posts")
	public List<Post> getPostsForUser(@PathVariable Long userId) {
		Optional<UserInfo> userOpt = userInfoRepository.findById(userId);
		if( userOpt.isEmpty() )
			throw new UserNotFoundException("ID: "+userId);
		
		return userOpt.get().getPosts();
	}
	
	@GetMapping(path="/jpa/ui/users/{userId}/posts/{postId}")
	public Post getPostsForUser(@PathVariable Long userId, @PathVariable Integer postId) {
		Optional<Post> postOpt = postRepository.findById(postId);
		if( postOpt.isEmpty() )
			throw new UserNotFoundException("ID: "+postId);
		
		return postOpt.get();
	}
	
	@PostMapping(path="/jpa/ui/users/{userId}/posts")
	public ResponseEntity<Post> createPostForUser(@PathVariable Long userId, @RequestBody Post post) {
		Optional<UserInfo> userOpt = userInfoRepository.findById(userId);
		if( userOpt.isEmpty() )
			throw new UserNotFoundException("ID: "+userId);
		
		post.setUserInfo(userOpt.get());
		
		Post savedPost = postRepository.save(post);
		
		URI uriLink = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{postId}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(uriLink).build();
		
	}
}
