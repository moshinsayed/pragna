package com.pragna.project.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pragna.project.entity.Page;
import com.pragna.project.entity.RecordFiles;
import com.pragna.project.service.RecordService;

@RestController
@RequestMapping("/record")
public class RecordController {
	
	@Autowired
	RecordService recordService;

	@PostMapping("/submit_audio")
	public ResponseEntity<?> submitAudio(@RequestParam MultipartFile file, @RequestParam("text") String text)
	{
		System.out.println(text);
		String output=recordService.submitAudio(file, text);
		return new ResponseEntity<String>(output, HttpStatus.OK);
	}
	
	@GetMapping("/audioURL/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
		// Load file from database
		Optional<RecordFiles> dbFile = recordService.getRecordFileData(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.get().getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.get().getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.get().getRecordfile()));
	}
}
