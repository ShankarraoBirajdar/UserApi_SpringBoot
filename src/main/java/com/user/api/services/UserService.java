package com.user.api.services;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.user.api.entities.User;

@Service
public class UserService {

	private static List<User> users = new ArrayList<>();

	static {

		users.add(new User(1, "Shankarrao Birajdar", 'M', 40000d));
		users.add(new User(2, "Priyanka Birajdar", 'F', 50000d));
		users.add(new User(3, "Onkar Gothankar", 'M', 45000d));
		users.add(new User(4, "Pooja Shinde", 'F', 60000d));
		users.add(new User(5, "Dhaneshwari Sutar", 'F', 55000d));
		users.add(new User(6, "Prashant Dake", 'M', 55000d));
		users.add(new User(7, "Aman Chauhan", 'M', 65000d));
	}

	public List<User> getAllUser() {
		return users;
	}

	public User getUserById(int id) {
		try {
			User user = users.stream().filter(e -> e.getId() == id).findFirst().get();
			return user;

		} catch (Exception e2) {
			return null;
		}

	}

	public boolean addUser(User user) {
		boolean isUserCreated = false;

		if (user != null) {
			users.add(user);
			isUserCreated = true;
		}
		return isUserCreated;
	}

	public boolean updateUserUsingPut(User user, int id) {
		boolean isUserUpdated = false;

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				users.get(i).setName(user.getName());
				users.get(i).setGender(user.getGender());
				users.get(i).setSalary(user.getSalary());
				isUserUpdated = true;
			}
		}
		
		return isUserUpdated;

	}
	
	public boolean updateUserUsingPatch(User user, int id) {
		boolean isUserUpdated = false;

		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				
				
				 if(user.getName()!=null) {
					 users.get(i).setName(user.getName());
				}
				 if(user.getSalary()!=0.0) {
					 users.get(i).setSalary(user.getSalary());
				 }
				 if(user.getGender()!=Character.MIN_VALUE) {
					 users.get(i).setGender(user.getGender());
				 }
				isUserUpdated = true;
			}
			
			
		}
		
		return isUserUpdated;

	}

	public boolean deleteUserById(int id) {
		boolean isUserDeleted = false;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getId() == id) {
				users.remove(i);
				isUserDeleted = true;
			}
		}
		return isUserDeleted;
	}

}
