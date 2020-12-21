package com.okky.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.okky.exception.Custom400Exception;
import com.okky.exception.DataException;

@Controller
public abstract class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle_base(Exception e) {
		log.info("" + e);
		StackTraceElement[] stack = e.getStackTrace();
		for (StackTraceElement s : stack) {
			log.info(s + "");
		}
		return new ResponseEntity<String>("{\"result\" : \"server_error\"}", HttpStatus.OK);
	}

	@ExceptionHandler({ Custom400Exception.class, MissingServletRequestParameterException.class })
	public ResponseEntity<String> handle_400(Exception e) {
			log.info(""+e);
			
		return new ResponseEntity<String>("{\"result\" : \"bad_request\"}", HttpStatus.OK);
	}

	@ExceptionHandler(DataException.class)
	public ResponseEntity<String> handle_data(DataException e) {
		StackTraceElement[] stack = e.getStackTrace();
		for (StackTraceElement s : stack) {
			log.info(s + "");
		}
		return new ResponseEntity<String>("{\"result\" : \"data_fail\"}", HttpStatus.OK);
	}
}
