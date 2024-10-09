package com.crimsonlogic.cms.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
	 private int userId;
	 private int id;
	 private String cargoId;
	 private String warehouseLocation;
	 private Double quantity;
	 private Timestamp lastUpdated;


}
