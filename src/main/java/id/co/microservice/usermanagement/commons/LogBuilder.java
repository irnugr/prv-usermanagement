package id.co.microservice.usermanagement.commons;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class LogBuilder {
	
	public String logBuilder(Timestamp timeStamp, String requestId, String message) {
		
		String logMessage = "at "+timeStamp.toString() +" with requestId "+requestId+" = "+message;
		
		return logMessage;
	}

}
