package com.parking.lot.controller;

import org.springframework.web.bind.annotation.*;
import com.parking.lot.dto.GenerateTicketRequestDto;
import com.parking.lot.dto.GenerateTicketResponseDto;
import com.parking.lot.exception.ErrorCode;
import com.parking.lot.exception.ParkingLotException;
import com.parking.lot.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @PostMapping("/generate")
    public GenerateTicketResponseDto generateTicket(@RequestBody GenerateTicketRequestDto request) {
        GenerateTicketResponseDto response = new GenerateTicketResponseDto();
        try {
            var ticket = ticketService.generateTicket(
                    request.getVehicleNumber(),
                    request.getVehicleType(),
                    request.getGateId());
            response.setTicket(ticket);
            response.setResponseStatus(ErrorCode.ERR_GENERATING_BILL_SUCCESS);
        } catch (ParkingLotException e) {
            response.setTicket(null);
            response.setResponseStatus(ErrorCode.ERR_GENERATING_BILL_FAILURE);
        }
        return response;
    }
}
