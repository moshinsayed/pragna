package com.pragna.project.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tbl_page")
@Data
@SequenceGenerator(name = "custom_sequence", sequenceName = "custom_seq", allocationSize = 1)
public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_sequence")
	private Long id;
	
	@Column(length = 1000)
	private String text;
	
	private String imageUrl;
	
	private String pageName;
	
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
