package id.co.microservice.usermanagement.service;

import java.util.Map;
import id.co.microservice.usermanagement.quecsentity.Users;

public interface UserManagementService {
	
	Map<String, Object> getUsernameAvailable(String requestId, String username);
	
	Map<String, Object> registerUser(String requestId, Users users);
	
	Map<String, Object> updatePassword(String requestId, Users users);

}
