package com.crimsonlogic.cms.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
	private int location_id;
	private String location_name;
	private String ship_name;
}
