package com.pragna.project.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pragna.project.entity.Page;
import com.pragna.project.jpa.PageRepository;
import com.pragna.project.model.PageModel;
import com.pragna.project.service.PageInformationService;

@Component
public class PageInformationServiceImpl implements PageInformationService {

	@Autowired
	PageRepository pageRepo;

	@Override
	public Long addPageInformation(MultipartFile file, String pageName, String sourceReference, String imageUrl,
			String text, String source) throws IOException {

		Page data = new Page();
		if (imageUrl.isEmpty()) {
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			data.setFileName(fileName);
			data.setFileType(file.getContentType());
			data.setImage(file.getBytes());
		}

		data.setImageUrl(imageUrl);
		data.setPageName(pageName);
		data.setSource(source);
		data.setSourceReference(sourceReference);
		data.setText(text);

		Page saved_data = pageRepo.save(data);
		Optional<Page> dbFile1 = pageRepo.findById(saved_data.getId());
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/page/imageURL/")
				.path(dbFile1.get().getId().toString()).toUriString();

		PageModel output = new PageModel();
		output.setPageId(saved_data.getId());
		output.setPageName(saved_data.getPageName());
		output.setText(saved_data.getText());
		if (imageUrl.isEmpty()) {
			output.setImageUrl(fileDownloadUri);
		} else {
			output.setImageUrl(imageUrl);
		}
		return saved_data.getId();
	}

	@Override
	public PageModel getPageById(Long id) {
		PageModel output = new PageModel();
		Optional<Page> data = pageRepo.findById(id);
		if (data.isPresent()) {
			Page saved_data = data.get();
			Optional<Page> dbFile1 = pageRepo.findById(saved_data.getId());
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/page/imageURL/")
					.path(dbFile1.get().getId().toString()).toUriString();

			output.setPageId(saved_data.getId());
			output.setPageName(saved_data.getPageName());
			output.setText(saved_data.getText());
			if (saved_data.getImageUrl().isEmpty()) {
				output.setImageUrl(fileDownloadUri);
			} else {
				output.setImageUrl(saved_data.getImageUrl());
			}
		} else {
			output.setPageId(0L);
		}
		return output;
	}

	@Override
	public Optional<Page> getFileData(Long fileId) {
		return pageRepo.findById(fileId);
	}

	@Override
	public Long getPreviousEntity(Long id) {
		Sort.Order orderById = Sort.Order.asc("id");
		Sort sort = Sort.by(orderById);
		List<Page> entities = pageRepo.findAll(sort);

		int index = -1;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getId().equals(id)) {
				index = i;
				break;
			}
		}

		if (index > 0) {
			return entities.get(index - 1).getId();
		} else {
			return null; // no previous entity
		}
	}

	@Override
	public Long getNextEntity(Long id) {
		Sort.Order orderById = Sort.Order.asc("id");
		Sort sort = Sort.by(orderById);
		List<Page> entities = pageRepo.findAll(sort);

		int index = -1;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).getId().equals(id)) {
				index = i;
				break;
			}
		}

		if (index < entities.size() - 1) {
			return entities.get(index + 1).getId();
		} else {
			return null; // no next entity
		}
	}

}
