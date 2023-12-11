package com.example.RecruitmentService.service.impl;

import com.example.RecruitmentService.entity.Applicant;
import com.example.RecruitmentService.entity.Role;
import com.example.RecruitmentService.entity.User;
import com.example.RecruitmentService.mail.EmailDetails;
import com.example.RecruitmentService.mail.EmailService;
import com.example.RecruitmentService.repository.ApplicantRepository;
import com.example.RecruitmentService.repository.RoleRepository;
import com.example.RecruitmentService.repository.UserRepository;
import com.example.RecruitmentService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ApplicantRepository applicantRepository;
	private ApplicantServiceImpl applicantServiceImpl;
	private List<User> list ;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private EmailService emailService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ApplicantServiceImpl applicantServiceImpl,  ApplicantRepository applicantRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.list = userRepository.findAll();
		this.applicantServiceImpl=applicantServiceImpl;
		this.applicantRepository=applicantRepository;
	}

	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	public User save(User user)  {
		EmailDetails details = new EmailDetails(user.getUsername(),"Добро пожаловать на сервис оценки кинофильмов. Ваш логин:" + user.getUsername() +". Ваш пароль:"+user.getPassword(), "Регистрация на сервисе", null);
	    user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role role = list.isEmpty()? roleRepository.findByRole("ROLE_ADMIN") : roleRepository.findByRole("ROLE_USER");
		user.setRoles(new HashSet<>(List.of(role)));
		Applicant applicant=new Applicant();
		userRepository.save(user);
		applicant.setUser(user);
		applicantRepository.save(applicant);
		if (user.getUsername()!=null) {
			emailService.sendSimpleMail(details);
		}

		return userRepository.save(user);
	}

	public User saveRecruter(User user, String firstName, String lastName, String username, String password, String phone, String bio, String age, String s)  {
		EmailDetails details = new EmailDetails(username,"Добро пожаловать на сервис оценки кинофильмов. Ваш логин:" + user.getUsername() +". Ваш пароль:"+user.getPassword(), "Регистрация на сервисе", null);
		user.setPassword(this.bCryptPasswordEncoder.encode(password));
		user.setActive(true);
		Role role =  roleRepository.findByRole("ROLE_RECRUTER");
		user.setRoles(new HashSet<>(List.of(role)));
		userRepository.save(user);
		if (user.getUsername()!=null) {
			emailService.sendSimpleMail(details);
		}

		return userRepository.save(user);
	}

	public void banUser(Integer id) {
		User user = userRepository.findUserById(id).orElse(null);
		if (user!=null){
			if (user.isActive()){
				user.setActive(false);
			} else {
				user.setActive(true);
			}
		}
		userRepository.save(user);
	}

	public void update(Integer id, String firstName, String lastName, String phone, String bio, String age){
		User user = userRepository.findUserById(id).orElse(null);
		if (user!=null){
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setPhone(phone);
			user.setBio(bio);
			user.setAge(age);
		}
		userRepository.save(user);
	}

	@Override
	public void removeUser(Integer id) {
		User user = findUserById(id);
		userRepository.delete(user);
	}

	@Override
	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username)
				.orElseThrow(() -> new NoSuchElementException());
	}

	@Override
	public User findUserById(Integer id) {
		return userRepository.findUserById(id)
				.orElseThrow(()->new NoSuchElementException());
	}

	public User getUserByUserName (Principal principal){
		if(principal==null) return new User();
		String username=principal.getName();
		return userRepository.findUserByUsername(username).orElseThrow(()->new NoSuchElementException());
	}


}
