package id.co.microservice.usermanagement.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.co.microservice.commons.response.ResponseMessage;
import id.co.microservice.usermanagement.commons.RequestIDGenerator;
import id.co.microservice.usermanagement.quecsentity.Users;
import id.co.microservice.usermanagement.service.UserManagementService;

@RefreshScope
@RestController
@RequestMapping("/api/v1/usrmgmtwithauth")
public class UserManagementWithAuthController extends AbstractController {
	
	@Autowired
	UserManagementService userManagementService;
	
	@PatchMapping("/updatePassword")
	ResponseMessage<Map<String, Object>> updatePassword(
			@RequestParam(value = "requestId", required = false) String requestId,
			@RequestBody Users users,
			HttpServletResponse httpServletResponse) {
		
		requestId = requestId == null ? RequestIDGenerator.getID() : requestId;
		
		Map<String, Object> response = userManagementService.updatePassword(requestId, users);
		
		return this.buildResponse(requestId, response, HttpStatus.OK.value(), HttpStatus.OK.name(), "");
		
	}

}
