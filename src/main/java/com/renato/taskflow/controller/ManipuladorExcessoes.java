package com.renato.taskflow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ManipuladorExcessoes {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> manipularEntityNotFoundException(EntityNotFoundException e){
		return ResponseEntity.notFound().build();
	}
}
