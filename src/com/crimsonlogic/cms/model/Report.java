package com.crimsonlogic.cms.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Report {
	 private int id;
	 private String reportType;
	 private int generatedBy;
	 private Timestamp generatedAt;
	 private String reportData;


}
