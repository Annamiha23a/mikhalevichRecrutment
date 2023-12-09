package com.example.RecruitmentService.service;

import com.example.RecruitmentService.entity.User;

import java.util.List;

public interface UserService {
	List<User> findAllUsers();

	User save(User user);

	void removeUser(Integer id);

	User findUserByUsername(String username);

	User findUserById(Integer id);
}
