package id.co.microservice.usermanagement.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.microservice.usermanagement.quecsentity.Users;
import id.co.microservice.usermanagement.service.UserManagementService;

@RefreshScope
@RestController
@RequestMapping("/api/v1/usrmgmtnoauth/")
public class UserManagementNoAuthController {
	
	@Autowired
	UserManagementService userManagementService;
	
	@GetMapping("/checkUsernameAvailable/")
	public @ResponseBody
	Map<String, Object> getUsernameAvailable(
			@RequestParam(value="username", required=true) String username,
			HttpServletResponse httpServletResponse) {
		
		Map<String, Object> response = userManagementService.getUsernameAvailable(username);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return response;
	}
	
	@PostMapping("/registerUser")
	Map<String, Object> registerUser(
			@RequestBody Users users,
			HttpServletResponse httpServletResponse
			) {
		
		Map<String, Object> response = userManagementService.registerUser(users);
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return response;
		
	}

}
