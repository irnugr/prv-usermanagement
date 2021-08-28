package id.co.microservice.usermanagement.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.co.microservice.usermanagement.quecsentity.Users;
import id.co.microservice.usermanagement.service.UserManagementService;

@RefreshScope
@RestController
@RequestMapping("/api/v1/usrmgmtwithauth/")
public class UserManagementWithAuthController {
	
	@Autowired
	UserManagementService userManagementService;
	
	@PatchMapping("/updatePassword")
	Map<String, Object> updatePassword(@RequestBody Users users,
			HttpServletResponse httpServletResponse) {
		
		Map<String, Object> response = userManagementService.updatePassword(users);
		
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		return response;
		
	}

}
