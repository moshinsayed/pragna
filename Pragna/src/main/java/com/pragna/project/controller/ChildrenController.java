package com.pragna.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pragna.project.entity.ChildrenEntity;
import com.pragna.project.service.ChildService;

@RestController
@RequestMapping("/child")
public class ChildrenController {

	@Autowired
	ChildService childService;
	
	
	@PostMapping("/addChild")
	public ResponseEntity<?> addChild(@RequestParam("childName") String childName, @RequestParam("childDob") String childDob, @RequestParam("parentId") String parentId)
	{
		ChildrenEntity data=childService.addChild(childName, childDob, parentId);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
