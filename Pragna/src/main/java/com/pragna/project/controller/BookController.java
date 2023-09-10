package com.pragna.project.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pragna.project.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	BookService bookService;
	
	
	@PostMapping("/add_new_book")
	public ResponseEntity<?> addNewBook(@RequestParam("name") String bookName)
	{
		Long output= bookService.addNewBook(bookName);
		return new ResponseEntity<Object>(output, HttpStatus.OK);
	}
	
	@GetMapping("/get_all_books_with_pages")
	public ResponseEntity<?> getAllBooksWithPages()
	{
		List<Map<String, Object>> output= bookService.getAllBooksWithPages();
		return new ResponseEntity<Object>(output, HttpStatus.OK);
	}
	
	@PostMapping("/add_new_page")
	public ResponseEntity<?> addNewPage(@RequestParam("id") Long id, @RequestParam("pId") Long pId, @RequestParam("name") String pageName)
	{
		Long output= bookService.addNewPage(id, pId, pageName);
		return new ResponseEntity<Object>(output, HttpStatus.OK);
	}
	
	@GetMapping("/get_max_page_id")
	public ResponseEntity<?> getMaxPageId()
	{
		Long output= bookService.getMaxPageId();
		return new ResponseEntity<Object>(output, HttpStatus.OK);
	}
	
	@PostMapping("/update_page")
	public ResponseEntity<?> updatePage(@RequestParam("id") Long id, @RequestParam("name") String pageName)
	{
		Long output= bookService.updatePage(id, pageName);
		return new ResponseEntity<Object>(output, HttpStatus.OK);
	}
	
	@PostMapping("/delete_page")
	public ResponseEntity<?> deletePage(@RequestParam("id") Long id)
	{
		Long output= bookService.deletePage(id);
		return new ResponseEntity<Object>(output, HttpStatus.OK);
	}
	
	@PostMapping("/update_book")
	public ResponseEntity<?> updateBook(@RequestParam("source_id") Long source_id, @RequestParam("target_id") Long target_id, @RequestParam("move") String move_type)
	{
		Long output= bookService.updateBook(source_id, target_id, move_type);
		return new ResponseEntity<Object>(output, HttpStatus.OK);
	}
}
