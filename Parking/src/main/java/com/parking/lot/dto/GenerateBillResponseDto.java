package com.parking.lot.dto;

import com.parking.lot.entity.Bill;
import com.parking.lot.exception.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerateBillResponseDto {
    private ErrorCode responseStatus;
    private Bill bill;

    public GenerateBillResponseDto setResponseStatus(ErrorCode status) {
        this.responseStatus = status;
        return this;
    }

    public GenerateBillResponseDto setBill(Bill bill) {
        this.bill = bill;
        return this;
    }
}
