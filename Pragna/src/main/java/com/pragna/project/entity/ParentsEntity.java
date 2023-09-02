package com.pragna.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "tbl_parents")
@Data
public class ParentsEntity {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customUserIdGenerator")
	 @GenericGenerator(name = "customUserIdGenerator", strategy = "com.pragna.project.entity.seqvence.CustomUserIdGenerator")
	 private String parentId;

    private String userName;

    private String userEmail;

    private String userPhone;

    private String userPassword;

    private Boolean verify = false;

    
}
