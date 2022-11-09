package org.koreait.commons;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("org.koreait.controllers")
public class CommonErrorHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JsonData<Object>> errorHandle(Exception e) {
		JsonData<Object> data = new JsonData<>();
		data.setSuccess(false);
		data.setMessage(e.getMessage());
		e.printStackTrace();
		
		return ResponseEntity.ok(data);
	}
}
