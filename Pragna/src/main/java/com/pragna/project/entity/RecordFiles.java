package com.pragna.project.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="tbl_record_data")
@Data
public class RecordFiles {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	private Long pageId;
	
	@Lob
	@Column(name="recordfile",length = 100000)
    private byte[] recordfile;

	@Column(name="fileName")
    private String fileName;

	@Column(name="fileType")
    private String fileType;
}
