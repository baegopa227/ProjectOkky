package com.okky;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okky.utils.JwtUtil;


@Controller
@RequestMapping("/")
public class HomeController {

	//ArrayList<TESTVO> list = new ArrayList<TESTVO>();
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value= {"/"})
	public String home(Model model) {
		model.addAttribute("test", "test");
		
		return "/index";
	}
	
	@RequestMapping(value= {"/test"})
	public String test(Model model) {
		
		return "/index";
	}
	

	@RequestMapping(value = "/test/webtoken")
	public ResponseEntity<String> webtoken() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "bearer test");
		
		return new ResponseEntity<String>("\"test\":\"test\"", headers, HttpStatus.OK);
	}
	
	
}
