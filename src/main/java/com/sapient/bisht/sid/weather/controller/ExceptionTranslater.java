package com.sapient.bisht.sid.weather.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sapient.bisht.sid.weather.dto.ErrorMessage;

@RestControllerAdvice
public class ExceptionTranslater {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handle(Exception ex,
			HttpServletRequest request, HttpServletResponse response) {
		ErrorMessage em = new ErrorMessage();
		em.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
		em.setErrorMsg("Invalid City name/country name. Please check and confirm.");
		if (ex instanceof NullPointerException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ErrorMessage>(em, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
