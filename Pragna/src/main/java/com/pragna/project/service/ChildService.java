package com.pragna.project.service;

import org.springframework.stereotype.Service;

import com.pragna.project.entity.ChildrenEntity;

@Service
public interface ChildService {

	ChildrenEntity addChild(String childName, String childDob, String parentId);

}
