package com.pragna.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.pragna.project.entity.QuestionEntity;

@Service
public interface QuestionsService {

	List<QuestionEntity> addData(String question, String[] options, String answer, Long pageId);

	List<QuestionEntity> findAll();

	List<QuestionEntity> findByPageId(Long pageId);

}
