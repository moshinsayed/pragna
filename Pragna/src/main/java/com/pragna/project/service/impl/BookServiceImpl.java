package com.pragna.project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pragna.project.entity.BookEntity;
import com.pragna.project.entity.Page;
import com.pragna.project.jpa.BookRepository;
import com.pragna.project.jpa.PageRepository;
import com.pragna.project.service.BookService;

@Component
public class BookServiceImpl implements BookService{

	@Autowired
	BookRepository bookRepo;
	
	@Autowired
	PageRepository pageRepo;
	
	@Override
	public Long addNewBook(String bookName) {
		BookEntity data=new BookEntity();
		data.setName(bookName);
		data.setPId(0L);
		BookEntity output=bookRepo.save(data);
		return output.getId();
	}

	@Override
	public List<Map<String, Object>> getAllBooksWithPages() {

		List<Map<String, Object>> output=new ArrayList<>();
		List<BookEntity> bookData=bookRepo.findAll();
		List<Page> pageData=pageRepo.findAll();
		for(BookEntity book:bookData)
		{
			Map<String, Object> data=new HashMap<>();
			data.put("id", book.getId());
			data.put("pId", book.getPId());
			data.put("name", book.getName());
			data.put("open", false);
			output.add(data);
		}
		
		for(Page page:pageData)
		{
			Map<String, Object> data=new HashMap<>();
			data.put("id", page.getId());
			data.put("pId", page.getPId());
			data.put("name", page.getName());
			data.put("file", "/adminPageRoting/"+page.getId());
			output.add(data);
		}
		return output;
	}

	@Override
	public Long addNewPage(Long id, Long pId, String pageName) {
		
		Page data=new Page();
		data.setId(id);
		data.setPId(pId);
		data.setName(pageName);
		
		Page out=pageRepo.save(data);
		
		return out.getId();
	}

	@Override
	public Long getMaxPageId() {
		if(pageRepo.getMaxPageId() == null)
		{
			return 0L;
		}
		else {
			return pageRepo.getMaxPageId();
		}
	}

	@Override
	public Long updatePage(Long id, String pageName) {
		Optional<Page> data=pageRepo.findById(id);
		Long out=0L;
		if(data.isPresent())
		{
			Page p=data.get();
			p.setName(pageName);
			pageRepo.save(p);
			out++;
		}
		return out;
	}

	@Override
	public Long deletePage(Long id) {
		pageRepo.deleteById(id);
		return 1L;
	}

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
    @Transactional
    public Long updateBook(Long source_id, Long target_id, String move_type) {
       
//		// Step 1: Update source_id to a temporary value
//            int aa= pageRepo.updateSourceToTemp(source_id);
//            
//            // Step 2: Update target_id to source_id
//            int aa2= pageRepo.updateTargetToSource(source_id, target_id);
//            
//            // Step 3: Update the temporary value to target_id
//            int aa3= pageRepo.updateTempToTarget(target_id);
       
            // Step 1: Update target_id to a temporary value
            int bb=  pageRepo.updateTargetToTemp(target_id);
            
            // Step 2: Update source_id to target_id
            int bb2= pageRepo.updateSourceToTarget(target_id, source_id);
            
            // Step 3: Update the temporary value to source_id
            int bb3= pageRepo.updateTempToSource(source_id);
        

        return target_id;
    }

}
