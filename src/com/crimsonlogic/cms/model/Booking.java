package com.crimsonlogic.cms.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	 private int bookingId;
	 private String cargoId;
	 private int userId;	
	 private Timestamp bookingDate;
	 private Timestamp scheduledDate;
	 private String status;

}
