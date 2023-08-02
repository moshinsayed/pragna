package com.pragna.project.service.impl;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pragna.project.entity.Page;
import com.pragna.project.entity.RecordFiles;
import com.pragna.project.jpa.RecordFileRepository;
import com.pragna.project.service.RecordService;
import com.pragna.project.util.SpeechSuperApi;

@Component
public class RecordServiceImpl implements RecordService{

	@Autowired
	SpeechSuperApi speech;
	
	@Autowired
	RecordFileRepository saveRepo;
	
	@Override
	public String submitAudio(MultipartFile file, String text) {
		
		RecordFiles data=new RecordFiles();
		 try {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		data.setFileName(fileName);
        data.setFileType(file.getContentType());
        data.setRecordfile(file.getBytes());
        data.setPageId(2L);
        
        RecordFiles saved_data=saveRepo.save(data);
        Optional<RecordFiles> dbFile1 = saveRepo.findById(saved_data.getId());
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/record/audioURL/")
					.path(dbFile1.get().getId().toString()).toUriString();
		System.out.println(fileDownloadUri);
        
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		String coreType = "sent.eval"; // Change the coreType according to your needs.
        String refText = text; // Change the reference text according to your needs.
        String audioPath = "supermarket.wav"; // Change the audio path corresponding to the reference text.
        String audioType = "wav"; // Change the audio type corresponding to the audio file.
        String audioSampleRate = "16000";
        String result = speech.HttpAPI(file, audioPath, audioType, audioSampleRate, refText, coreType);

		
		return result;
	}

	@Override
	public Optional<RecordFiles> getRecordFileData(Long fileId) {
		return saveRepo.findById(fileId);
	}

	
}
