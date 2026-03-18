package com.yash.bulk_processing.utils;

import org.apache.commons.csv.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.yash.bulk_processing.dtos.CsvRowDto;

import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvParser {

	public List<CsvRowDto> parse(MultipartFile file) throws Exception {

		List<CsvRowDto> list = new ArrayList<>();

		try (Reader reader = new InputStreamReader(file.getInputStream());
				CSVParser csvParser = new CSVParser(reader,
						CSVFormat.DEFAULT.builder().setHeader().setSkipHeaderRecord(true).build())) {

			for (CSVRecord record : csvParser) {
				CsvRowDto dto = new CsvRowDto();
				dto.setRecordId(record.get("record_id"));
				dto.setGroupId(record.get("group_id"));
				dto.setGroupName(record.get("group_name"));
				dto.setItemName(record.get("item_name"));
				dto.setQuantity(Integer.parseInt(record.get("quantity")));
				dto.setPrice(Double.parseDouble(record.get("price")));
				dto.setCreatedDate(LocalDate.parse(record.get("created_date")));
				list.add(dto);
			}
		}

		return list;
	}
}