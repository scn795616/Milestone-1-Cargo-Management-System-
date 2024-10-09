package com.crimsonlogic.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {
	private int uesr_id;
	private String id;
	 private String description;
	 private double weight;
	 private String origin;
	 private String destination;
	 private String status;


}
