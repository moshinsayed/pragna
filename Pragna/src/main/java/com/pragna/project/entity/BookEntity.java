package com.pragna.project.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "tbl_book")
@Data
@SequenceGenerator(name = "book_sequence", sequenceName = "book_seq", allocationSize = 1, initialValue = 1)
public class BookEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
	private Long id;
	
	private Long pId;
	
	private String name;	
}
