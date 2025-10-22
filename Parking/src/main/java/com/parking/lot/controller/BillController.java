package com.parking.lot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.parking.lot.dto.GenerateBillRequestDto;
import com.parking.lot.dto.GenerateBillResponseDto;
import com.parking.lot.exception.ErrorCode;
import com.parking.lot.exception.ParkingLotException;
import com.parking.lot.service.BillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bill")
@RequiredArgsConstructor
public class BillController {

    private final BillService billService;
    
    @PostMapping("/generate")
    public GenerateBillResponseDto generateBill(@RequestBody GenerateBillRequestDto request) {
        
        GenerateBillResponseDto response = new GenerateBillResponseDto();
        try {
            var bill = billService.generateBill(request.getVehicleNumber(), request.getGateId());
            response.setBill(bill);
            response.setResponseStatus(ErrorCode.ERR_GENERATING_BILL_SUCCESS);
        } catch (ParkingLotException e) {
            response.setBill(null);
            response.setResponseStatus(ErrorCode.ERR_GENERATING_BILL_FAILURE);
        }
        return response;
    }
}
