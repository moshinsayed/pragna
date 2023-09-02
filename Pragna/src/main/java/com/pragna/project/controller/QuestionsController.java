package com.pragna.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pragna.project.entity.QuestionEntity;
import com.pragna.project.service.QuestionsService;

@RestController
@RequestMapping("/questions")
public class QuestionsController {

	@Autowired
	QuestionsService questionService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addData(@RequestParam("question") String question, @RequestParam("options") String[] options, @RequestParam("answer") String answer, @RequestParam("pageId") Long pageId)
	{
		List<QuestionEntity> data=questionService.addData(question, options, answer, pageId);
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}
	
	@PostMapping("/findbyid")
	public ResponseEntity<?> findByPageId(@RequestParam("pageId") Long pageId)
	{
		List<QuestionEntity> data=questionService.findByPageId(pageId);
		return new ResponseEntity<Object>(data, HttpStatus.OK);
	}
}
