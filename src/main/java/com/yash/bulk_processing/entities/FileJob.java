package com.yash.bulk_processing.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "file_jobs")
public class FileJob {

	@Id
	private String jobId;

	private String status;

	private int totalRecords;
	private int processedRecords;
	private int failedRecords;
}
