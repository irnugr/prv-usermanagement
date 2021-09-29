package id.co.microservice.usermanagement.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import id.co.microservice.commons.response.ResponseMessage;
import id.co.microservice.usermanagement.commons.RequestIDGenerator;
import id.co.microservice.usermanagement.quecsentity.Users;
import id.co.microservice.usermanagement.service.UserManagementService;

@RefreshScope
@RestController
@RequestMapping("/api/v1/usrmgmtnoauth")
public class UserManagementNoAuthController extends AbstractController {
	
	@Autowired
	UserManagementService userManagementService;
	
	@GetMapping("/checkUsernameAvailable/")
	public @ResponseBody
	ResponseMessage<Map<String, Object>> getUsernameAvailable(
			@RequestParam(value = "requestId", required = false) String requestId,
			@RequestParam(value="username", required=true) String username,
			HttpServletResponse httpServletResponse) {
		
		requestId = requestId == null ? RequestIDGenerator.getID() : requestId;
		
		Map<String, Object> response = userManagementService.getUsernameAvailable(requestId, username);
		
		return this.buildResponse(requestId, response, HttpStatus.OK.value(), HttpStatus.OK.name(), "");
	}
	
	@PostMapping("/registerUser")
	ResponseMessage<Map<String, Object>> registerUser(
			@RequestParam(value = "requestId", required = false) String requestId,
			@RequestBody Users users,
			HttpServletResponse httpServletResponse
			) {
		
		requestId = requestId == null ? RequestIDGenerator.getID() : requestId;
		
		Map<String, Object> response = userManagementService.registerUser(requestId, users);
		
		return this.buildResponse(requestId, response, HttpStatus.OK.value(), HttpStatus.OK.name(), "");
		
	}

}
