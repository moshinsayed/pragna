package com.pragna.project.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pragna.project.entity.Page;
import com.pragna.project.model.PageModel;

@Service
public interface PageInformationService {

	Long addPageInformation(MultipartFile file, Long id, String sourceReference, String text, String source) throws IOException;

	PageModel getPageById(Long id);

	Optional<Page> getFileData(Long fileId);

	Long getPreviousEntity(Long id);

	Long getNextEntity(Long id);

}
