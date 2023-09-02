package com.pragna.project.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public interface BookService {

	public Long addNewBook(String bookName);

	public List<Map<String, Object>> getAllBooksWithPages();

	public Long addNewPage(Long id, Long pId, String pageName);

	public Long getMaxPageId();

	public Long updatePage(Long id, String pageName);

	public Long deletePage(Long id);

	public Long updateBook(Long source_id, Long target_id, String move_type);
	
}
