package id.co.microservice.usermanagement.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import id.co.microservice.usermanagement.service.UserManagementService;
import id.co.microservice.usermanagement.quecsrepository.UsersRepository;
import id.co.microservice.usermanagement.commons.LogBuilder;
import id.co.microservice.usermanagement.quecsentity.Users;

@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	LogBuilder logs;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	private Date date = new Date();
	private Timestamp timestampnow = new Timestamp(date.getTime());
	private static final Logger LOG = org.slf4j.LoggerFactory.getLogger(UserManagementServiceImpl.class);
	private String message = null;

	@Override
	public Map<String, Object> getUsernameAvailable(String requestId, String username) {
		
		Map<String, Object> response = new HashMap<>();
		
		try {
			
			Users users = usersRepository.getUsersByUsername(username);
			
			if (users != null) {
				message = "user found";
				response.put("usernamestatus", message);
				LOG.info(logs.logBuilder(timestampnow, requestId, message));
			} else {
				message = "user not found";
				response.put("usernamestatus", message);
				LOG.warn(logs.logBuilder(timestampnow, requestId, message));
			}
		} catch (Exception e) {
			message = e.getMessage();
			response.put("usernamestatus", message);
			LOG.error(logs.logBuilder(timestampnow, requestId, message));
		}
		
		return response;
	}

	@Override
	public Map<String, Object> registerUser(String requestId, Users users) {
		
		UUID usersUUID = UUID.randomUUID();
		Map<String, Object> response = new HashMap<>();
		String userRegisterPwd = bcryptEncoder.encode(users.getPassword());
		
		try {
			usersRepository.registerUser(usersUUID, users.getUsername(), userRegisterPwd, users.getEmail(), users.getFirstname(),
					users.getLastname(), timestampnow, users.getCreateBy());
			message = "Register success";
			response.put("response", message);
			LOG.info(logs.logBuilder(timestampnow, requestId, message));
		} catch (Exception e) {
			message = "Register user failed "+e.getMessage();
			response.put("response", message);
			LOG.error(logs.logBuilder(timestampnow, requestId, message));
		}
		
		return response;
	}

	@Override
	public Map<String, Object> updatePassword(String requestId, Users users) {
		
		Map<String, Object> response = new HashMap<>();
		String userUpdatePwd = bcryptEncoder.encode(users.getPassword());
		try {
			usersRepository.updatePassword(userUpdatePwd, timestampnow, users.getUpdateBy(), users.getId());
			message = "Update password success";
			response.put("response", message);
			LOG.info(logs.logBuilder(timestampnow, requestId, message));
		} catch (Exception e) {
			message = "Update password failed "+ e.getMessage();
			response.put("response", message);
			LOG.error(logs.logBuilder(timestampnow, requestId, message));
		}
		
		return response;
	}

}
