package com.crimsonlogic.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ship {
	private String cargoId;
	private String userName;
	private String userNumber;
	private String shipName;
	private String shipNumber;
	private String origin;
	private String destination;
	private String item;
	private Double weight;
	

}
