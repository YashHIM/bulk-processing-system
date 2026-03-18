package com.yash.bulk_processing.serviceImpls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.bulk_processing.dtos.CsvRowDto;
import com.yash.bulk_processing.entities.FileJob;
import com.yash.bulk_processing.entities.Group;
import com.yash.bulk_processing.entities.GroupItem;
import com.yash.bulk_processing.services.FileJobService;
import com.yash.bulk_processing.services.FileService;
import com.yash.bulk_processing.services.GroupItemService;
import com.yash.bulk_processing.services.GroupService;
import com.yash.bulk_processing.utils.CsvParser;

@Service
public class FileServiceImpl implements FileService {

	private final CsvParser parser;
	private final FileJobService jobService;
	private final GroupService groupService;
	private final GroupItemService itemService;

	public FileServiceImpl(CsvParser parser, FileJobService jobService, GroupService groupService,
			GroupItemService itemService) {
		this.parser = parser;
		this.jobService = jobService;
		this.groupService = groupService;
		this.itemService = itemService;
	}

	@Override
	public String uploadFile(MultipartFile file) {
		String jobId = UUID.randomUUID().toString();
		jobService.createJob(jobId);
		processFileAsync(file, jobId);
		return jobId;
	}

	@Async
	public void processFileAsync(MultipartFile file, String jobId) {
		try {
			List<CsvRowDto> rows = parser.parse(file);

			FileJob job = jobService.getJob(jobId);
			job.setTotalRecords(rows.size());

			Set<String> itemSet = new HashSet<>();

			int processed = 0;
			int failed = 0;

			List<GroupItem> batch = new ArrayList<>();

			for (CsvRowDto row : rows) {
				try {
					validate(row);

					Group group = groupService.getOrCreateGroup(row.getGroupId(), row.getGroupName());

					String key = row.getGroupId() + "_" + row.getItemName();

					if (!itemSet.contains(key)) {
						itemSet.add(key);

						GroupItem item = new GroupItem();
						item.setRecordId(row.getRecordId());
						item.setItemName(row.getItemName());
						item.setQuantity(row.getQuantity());
						item.setPrice(row.getPrice());
						item.setCreatedDate(row.getCreatedDate());
						item.setGroup(group);
						batch.add(item);
					}

					if (!batch.isEmpty()) {
						itemService.saveBatch(batch);
					}

					if (batch.size() == 1000) {
						itemService.saveBatch(batch);
						batch.clear();
					}

					processed++;

				} catch (Exception e) {
					failed++;
				}
			}

			if (!batch.isEmpty()) {
				itemService.saveBatch(batch);
			}

			job.setProcessedRecords(processed);
			job.setFailedRecords(failed);
			job.setStatus("COMPLETED");

			jobService.updateJob(job);

		} catch (Exception e) {
			FileJob job = jobService.getJob(jobId);
			job.setStatus("FAILED");
			jobService.updateJob(job);
		}
	}

	private void validate(CsvRowDto row) {
		if (row.getRecordId() == null || row.getGroupId() == null || row.getItemName() == null) {
			throw new RuntimeException();
		}
		if (row.getQuantity() <= 0 || row.getPrice() <= 0) {
			throw new RuntimeException();
		}
	}

	@Override
	public FileJob getStatus(String jobId) {
		return jobService.getJob(jobId);
	}
}
