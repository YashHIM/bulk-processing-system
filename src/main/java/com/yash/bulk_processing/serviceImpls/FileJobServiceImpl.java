package com.yash.bulk_processing.serviceImpls;

import org.springframework.stereotype.Service;

import com.yash.bulk_processing.entities.FileJob;
import com.yash.bulk_processing.repositories.FileJobRepository;
import com.yash.bulk_processing.services.FileJobService;

@Service
public class FileJobServiceImpl implements FileJobService {

	private final FileJobRepository fileJobRepository;

	public FileJobServiceImpl(FileJobRepository fileJobRepository) {
		this.fileJobRepository = fileJobRepository;
	}

	@Override
	public FileJob createJob(String jobId) {
		FileJob job = new FileJob();
		job.setJobId(jobId);
		job.setStatus("PROCESSING");
		return fileJobRepository.save(job);
	}

	@Override
	public FileJob getJob(String jobId) {
		return fileJobRepository.findById(jobId).orElseThrow();
	}

	@Override
	public void updateJob(FileJob job) {
		fileJobRepository.save(job);
	}
}
