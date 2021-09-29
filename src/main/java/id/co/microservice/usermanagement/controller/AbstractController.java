package id.co.microservice.usermanagement.controller;

import id.co.microservice.commons.response.ResponseMessage;
import id.co.microservice.usermanagement.commons.RequestIDGenerator;

public class AbstractController {
	
	protected <T> ResponseMessage<T> buildResponse(final String requestIdWeb, final T result,
			int httpStatus, String statusCode, String desc) {
		final String requestId = (requestIdWeb == null) ? RequestIDGenerator.getID() : requestIdWeb;
		final ResponseMessage<T> msg = new ResponseMessage<>(requestId);
		msg.buildResponseMessage(result, httpStatus, statusCode, desc);
		return msg;
	}

}
