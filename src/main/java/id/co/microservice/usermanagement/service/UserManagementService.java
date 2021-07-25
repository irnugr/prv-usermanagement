package id.co.microservice.usermanagement.service;

import java.util.Map;
import id.co.microservice.usermanagement.quecsentity.Users;

public interface UserManagementService {
	
	Map<String, Object> getUsernameAvailable(String username);
	
	Map<String, Object> registerUser(Users users);
	
	Map<String, Object> updatePassword(Users users);

}
