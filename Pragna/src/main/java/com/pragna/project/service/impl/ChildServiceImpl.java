package com.pragna.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pragna.project.entity.ChildrenEntity;
import com.pragna.project.jpa.ChildrensRepo;
import com.pragna.project.service.ChildService;

@Component
public class ChildServiceImpl implements ChildService{

	@Autowired
	ChildrensRepo childRepo;
	
	@Override
	public ChildrenEntity addChild(String childName, String childDob, String parentId) {
	
		ChildrenEntity data=new ChildrenEntity();
		data.setChildName(childName);
		data.setChildDob(childDob);
		data.setParentId(parentId);
		
		ChildrenEntity output=childRepo.save(data);
		return output;
	}

	
}
