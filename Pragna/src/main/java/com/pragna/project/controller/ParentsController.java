package com.pragna.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pragna.project.entity.ChildrenEntity;
import com.pragna.project.entity.ParentsEntity;
import com.pragna.project.service.ParentsService;

@RestController
@RequestMapping("/user")
public class ParentsController {

	@Autowired
	private ParentsService userService;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> registerUser(@RequestParam("userName") String userName, @RequestParam("userEmail") String userEmail, @RequestParam("userPhone") String userPhone,@RequestParam("userPassword") String userPassword)
	{
		ParentsEntity data=userService.registerUser(userName, userEmail, userPhone, userPassword);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping("/loginUser")
	public ResponseEntity<?> loginUser(@RequestParam("loginEmail") String loginEmail, @RequestParam("loginPassword") String loginPassword)
	{
		ParentsEntity data=userService.loginUser(loginEmail, loginPassword);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	@PostMapping("/loginWithVerifyUser")
	public ResponseEntity<?> loginWithVerifyUser(@RequestParam("loginEmail") String loginEmail, @RequestParam("loginPassword") String loginPassword)
	{
		ParentsEntity data=userService.loginWithVerifyUser(loginEmail, loginPassword);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
