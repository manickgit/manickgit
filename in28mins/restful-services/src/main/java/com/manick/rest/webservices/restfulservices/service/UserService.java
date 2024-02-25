package com.manick.rest.webservices.restfulservices.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.manick.rest.webservices.restfulservices.entity.User;

@Component
public class UserService {

	private static List<User> users = new ArrayList<>();
	private static long userCount = 0;
	
	static {
		users.add(new User(++userCount, "Manick", LocalDate.now().minusYears(39)));
		users.add(new User(++userCount, "Raj", LocalDate.now().minusYears(25)));
		users.add(new User(++userCount, "Shwetha", LocalDate.now().minusYears(28)));
		users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(45)));
	}
	
	public List<User> getAllUsers() {
		return users;
	}
	
	public User getOneUser(Long id) {
		Predicate<User> predicate = user -> user.getId().equals(id);
		
		return users.stream().filter(predicate).findFirst().orElse(null);
		
	}
	
	public User saveUser(User user) {
		
		user.setId(++userCount);
		users.add(user);
		return user;
	}

	public void delete(Long userId) {

		Predicate<? super User> userIdMatch = user -> user.getId().equals(userId);
		users.removeIf(userIdMatch);
	}
}
