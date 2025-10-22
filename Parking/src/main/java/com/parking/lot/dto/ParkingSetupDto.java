package com.parking.lot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingSetupDto {
    private String gateId;
    private String gateType;
    private Integer gateNumber;

    private String vehicleNumber;
    private String vehicleType;

    private String floorId;
    private Integer levelNumber;

    private String parkingId;
    private String spotType;
    private String spotStatus;
}
