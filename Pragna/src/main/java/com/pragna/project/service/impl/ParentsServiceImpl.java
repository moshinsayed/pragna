package com.pragna.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pragna.project.entity.ChildrenEntity;
import com.pragna.project.entity.ParentsEntity;
import com.pragna.project.jpa.ParentsRepository;
import com.pragna.project.service.ParentsService;
import com.pragna.project.util.EmailCode;

@Component
public class ParentsServiceImpl implements ParentsService{
	
	@Autowired
	ParentsRepository userRepo;
	
	@Autowired
	EmailCode emailCode;

	@Override
	public ParentsEntity registerUser(String userName, String userEmail, String userPhone, String userPassword) {
		
		ParentsEntity data=new ParentsEntity();
		data.setUserName(userName);
		data.setUserEmail(userEmail);
		data.setUserPhone(userPhone);
		data.setUserPassword(userPassword);
		
		ParentsEntity result=userRepo.save(data);
		
		emailCode.verifyUserEmail(userEmail, userName);
		return result;
	}

	@Override
	public ParentsEntity loginUser(String loginEmail, String loginPassword) {
		
		Optional<ParentsEntity> data=userRepo.loginUser(loginEmail, loginPassword);
		if(data.isPresent())
		{
			if(data.get().getVerify() == true)
			{
				return data.get();
			}
			else
			{
				return new ParentsEntity();
			}
			
		}
		else
		{
			return null;
		}
		
	}

	@Override
	public ParentsEntity loginWithVerifyUser(String loginEmail, String loginPassword) {
		Optional<ParentsEntity> data=userRepo.loginUser(loginEmail, loginPassword);
		if(data.isPresent())
		{
			
				ParentsEntity aa=data.get();
				aa.setVerify(true);
				
				return userRepo.save(aa);
		}
		else
		{
			return null;
		}
	}

}
