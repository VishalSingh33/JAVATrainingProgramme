package com.parking.lot.dto;

import com.parking.lot.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerateTicketRequestDto {
    private String vehicleNumber;
    private VehicleType vehicleType;
    private String gateId;
}
