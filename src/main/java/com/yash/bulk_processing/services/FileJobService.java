package com.yash.bulk_processing.services;

import com.yash.bulk_processing.entities.FileJob;

public interface FileJobService {
	FileJob createJob(String jobId);

	FileJob getJob(String jobId);

	void updateJob(FileJob job);
}
