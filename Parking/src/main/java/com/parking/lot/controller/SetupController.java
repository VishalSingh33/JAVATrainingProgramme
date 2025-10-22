package com.parking.lot.controller;


import com.parking.lot.dto.ParkingSetupDto;
import com.parking.lot.service.ParkingSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
public class SetupController {

    private final ParkingSetupService setupService;

    @PostMapping("/setup")
    public ResponseEntity<String> createSetup(@RequestBody ParkingSetupDto request) {
        setupService.createParkingSetup(request);
        return ResponseEntity.ok("Parking setup created successfully!");
    }
}
