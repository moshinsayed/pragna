package com.pragna.project.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pragna.project.entity.RecordFiles;

@Service
public interface RecordService {

	String submitAudio(MultipartFile file, String text);

	Optional<RecordFiles> getRecordFileData(Long fileId);

}
