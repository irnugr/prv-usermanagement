package id.co.microservice.usermanagement.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.co.microservice.usermanagement.service.UserManagementService;
import id.co.microservice.usermanagement.quecsrepository.UsersRepository;
import id.co.microservice.usermanagement.quecsentity.Users;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	private Date date = new Date();
	private Timestamp timestampnow = new Timestamp(date.getTime());

	@Override
	public Map<String, Object> getUsernameAvailable(String username) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Users users = usersRepository.getUsersByUsername(username);
			
			if (users != null) {
				response.put("users", users);
			} else {
				response.put("users", "not found");
			}
		} catch (Exception e) {
			response.put("error", e.getMessage());
		}
		
		return response;
	}

	@Override
	public Map<String, Object> registerUser(Users users) {
		
		UUID usersUUID = UUID.randomUUID();
		Map<String, Object> response = new HashMap<>();
		String userRegisterPwd = bcryptEncoder.encode(users.getPassword());
		
		try {
			usersRepository.registerUser(usersUUID, users.getUsername(), userRegisterPwd, users.getEmail(), users.getFirstname(),
					users.getLastname(), timestampnow, users.getCreateBy());
			response.put("response", "Register success");
		} catch (Exception e) {
			response.put("response", "Register user failed "+e.getMessage());
		}
		
		return response;
	}

	@Override
	public Map<String, Object> updatePassword(Users users) {
		
		Map<String, Object> response = new HashMap<>();
		String userUpdatePwd = bcryptEncoder.encode(users.getPassword());
		
		try {
			usersRepository.updatePassword(userUpdatePwd, timestampnow, users.getUpdateBy(), users.getId());
			response.put("response", "Update password success");
		} catch (Exception e) {
			response.put("response", "Update password failed "+ e.getMessage());
		}
		
		return response;
	}

}
