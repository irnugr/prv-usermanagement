package id.co.microservice.usermanagement.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/api/v1/")
public class UserManagementController {
	
	@GetMapping("/helloworld")
	public @ResponseBody
	String getHelloWorld(HttpServletResponse httpServletResponse) {
		return "Hello World";
	}

}
