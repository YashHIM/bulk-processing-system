package com.yash.bulk_processing.services;

import org.springframework.web.multipart.MultipartFile;

import com.yash.bulk_processing.entities.FileJob;

public interface FileService {
	String uploadFile(MultipartFile file);

	FileJob getStatus(String jobId);
}
