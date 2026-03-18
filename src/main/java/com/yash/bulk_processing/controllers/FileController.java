package com.yash.bulk_processing.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.bulk_processing.entities.FileJob;
import com.yash.bulk_processing.services.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {

	private final FileService fileService;

	public FileController(FileService fileService) {
		this.fileService = fileService;
	}

	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile file) {
		try {
			String jobId = fileService.uploadFile(file);
			return ResponseEntity.ok(jobId);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Upload failed");
		}
	}

	@GetMapping("/status/{jobId}")
	public ResponseEntity<?> status(@PathVariable String jobId) {
		try {
			FileJob job = fileService.getStatus(jobId);
			return ResponseEntity.ok(job);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Invalid jobId");
		}
	}
}
