package com.yash.bulk_processing.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CsvRowDto {

	private String recordId;
	private String groupId;
	private String groupName;
	private String itemName;
	private Integer quantity;
	private Double price;
	private LocalDate createdDate;
}
