package com.pragna.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tbl_page")
@Data
public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	private Long pId;
	
	@Column(length = 1000)
	private String text;
	
	private String name;
	
	private String sourceReference;
	
	private String source;
	
	@Lob
	@Column(name="image",length = 100000)
    private byte[] image;

	@Column(name="fileName")
    private String fileName;

	@Column(name="fileType")
    private String fileType;
	
	
}
