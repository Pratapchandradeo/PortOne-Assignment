package com.stripe_payment_gateway.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MyException.class)
	public ResponseEntity<ErrorDetails> myExceptionHandler(MyException myException, WebRequest webRequest){
		ErrorDetails errorDetail = new ErrorDetails();
		
		errorDetail.setDateTime(LocalDateTime.now());
		errorDetail.setMeassage(myException.getMessage());
		errorDetail.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(Exception exception, WebRequest webRequest){
		ErrorDetails errorDetail = new ErrorDetails();
		
		errorDetail.setDateTime(LocalDateTime.now());
		errorDetail.setMeassage(exception.getMessage());
		errorDetail.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
	}
}
