package com.pragna.project.service;

import org.springframework.stereotype.Service;

import com.pragna.project.entity.ChildrenEntity;
import com.pragna.project.entity.ParentsEntity;

@Service
public interface ParentsService {

	ParentsEntity registerUser(String userName, String userEmail, String userPhone, String userPassword);

	ParentsEntity loginUser(String loginEmail, String loginPassword);

	ParentsEntity loginWithVerifyUser(String loginEmail, String loginPassword);

}
