package com.fileupload.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AllExceptionHandle  {
	
	@ExceptionHandler(FileNotFoundException.class)
	public ResponseEntity<Map<String, String>> handlePlayerException(FileNotFoundException ex)
	{
		Map<String, String> errorMap = new HashMap<>();
		errorMap.put("errorMessage ",ex.getMessage());
		return new ResponseEntity<>(errorMap,HttpStatus.NOT_FOUND);		
	}
	
	

}
