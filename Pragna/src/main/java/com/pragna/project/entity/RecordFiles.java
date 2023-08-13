package com.pragna.project.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


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
