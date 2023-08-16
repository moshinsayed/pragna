package com.pragna.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pragna.project.entity.Page;
import com.pragna.project.model.PageModel;
import com.pragna.project.service.PageInformationService;

@RestController
@RequestMapping("/page")
public class PageInformationController {

	@Autowired
	PageInformationService pageService;

	@PostMapping("/add_new")
	public ResponseEntity<?> addPageInfo(@RequestParam(required = false) MultipartFile file,
			@RequestParam("pageName") String pageName, @RequestParam("sourceReference") String sourceReference,
			@RequestParam("imageUrl") String imageUrl, @RequestParam("text") String text,
			@RequestParam("source") String source) {
		Long result = null;
		try {
			result = pageService.addPageInformation(file, pageName, sourceReference, imageUrl, text, source);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@PostMapping("/getPageById")
	public ResponseEntity<?> getPageById(@RequestParam("pageId") Long id) {
		PageModel result = pageService.getPageById(id);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@PostMapping("/getPreviousNextIds")
	public ResponseEntity<?> getPreviousNextIds(@RequestParam("pageId") Long id) {
		Long previousId = pageService.getPreviousEntity(id);
		Long nextId = pageService.getNextEntity(id);
		Map<String, Long> result = new HashMap<String, Long>();
		result.put("previuos", previousId);
		result.put("next", nextId);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@GetMapping("/imageURL/{fileId}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
		// Load file from database
		Optional<Page> dbFile = pageService.getFileData(fileId);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(dbFile.get().getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + dbFile.get().getFileName() + "\"")
				.body(new ByteArrayResource(dbFile.get().getImage()));
	}
}
