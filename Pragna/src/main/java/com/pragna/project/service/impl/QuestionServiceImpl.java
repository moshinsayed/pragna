package com.pragna.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import com.pragna.project.entity.QuestionEntity;
import com.pragna.project.jpa.QuestionsRepository;
import com.pragna.project.service.QuestionsService;

@Component
public class QuestionServiceImpl implements QuestionsService{

	@Autowired
	QuestionsRepository repo;
	
	@Override
	public List<QuestionEntity> addData(String question, String[] options, String answer, Long pageId) {
	
		QuestionEntity data=new QuestionEntity();
		data.setQuestion(question);
		data.setOptions(options);
		data.setAnswers(answer);
		data.setPageId(pageId);
		repo.save(data);
		
		return repo.findByPageId(pageId);
	}

	@Override
	public List<QuestionEntity> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public List<QuestionEntity> findByPageId(Long pageId) {
		List<QuestionEntity> data=repo.findByPageId(pageId);
		return data;
	}

}
