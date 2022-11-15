package com.user.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.api.entities.User;
import com.user.api.services.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userSevice;

	@GetMapping("/get/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = userSevice.getAllUser();
		if (list.size() > 0)
			return ResponseEntity.ok(list);

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/get/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
		User user = userSevice.getUserById(id);
		if (user == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.ok(user);

	}

	@PostMapping("/add/user")
	public ResponseEntity<?> addUser(@RequestBody User user) {
		boolean isUserCreated = userSevice.addUser(user);
		if (isUserCreated) {
			return new ResponseEntity<Object>("User Created Successfully",HttpStatus.CREATED);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		return new ResponseEntity<Object>("User Not Found",HttpStatus.NOT_FOUND);
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/delete/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		boolean isUserDeleted = userSevice.deleteUserById(id);
		if (isUserDeleted) {
			return new ResponseEntity<Object>("User Deleted Successfully",HttpStatus.NO_CONTENT);
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return new ResponseEntity<Object>("User Not Found",HttpStatus.NOT_FOUND);
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@PutMapping("/update/user/{id}")
	public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable("id") int id) {
		User user2 = userSevice.getUserById(id);
		if (user2 == null) {
			userSevice.addUser(user);
			return new ResponseEntity<Object>("User Created Successfully",HttpStatus.CREATED);
//			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		userSevice.updateUserUsingPut(user, id);
		return new ResponseEntity<Object>("User Updated Successfully",HttpStatus.OK);
//		return ResponseEntity.status(HttpStatus.OK).build();

	}

	@PatchMapping("/update/user/{id}")
	public ResponseEntity<?> updateUserPatch(@RequestBody User user, @PathVariable("id") int id) {

		boolean isUserUpdated = userSevice.updateUserUsingPatch(user, id);
		if (isUserUpdated) {
			return new ResponseEntity<Object>("User Updated Successfully",HttpStatus.OK);
//			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return new ResponseEntity<Object>("User Not Found",HttpStatus.NOT_FOUND);
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
