package com.parking.lot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateBillRequestDto {
    private String vehicleNumber;
    private String gateId;
}
