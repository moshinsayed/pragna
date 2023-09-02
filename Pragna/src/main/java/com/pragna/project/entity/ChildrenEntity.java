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
@Table(name = "tbl_childrens")
@Data
public class ChildrenEntity {

	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "customChildIdGenerator")
	 @GenericGenerator(name = "customChildIdGenerator", strategy = "com.pragna.project.entity.seqvence.CustomChildIdGenerator")
	 private String childId;
	
	private String childName;
	
	private String childDob;
	
	private String parentId;
	
}
