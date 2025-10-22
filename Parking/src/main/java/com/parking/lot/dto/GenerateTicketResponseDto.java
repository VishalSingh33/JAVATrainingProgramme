package com.parking.lot.dto;

import com.parking.lot.entity.Ticket;
import com.parking.lot.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenerateTicketResponseDto {
    private ErrorCode responseStatus;
    private Ticket ticket;

    public GenerateTicketResponseDto setResponseStatus(ErrorCode status) {
        this.responseStatus = status;
        return this;
    }

    public GenerateTicketResponseDto setTicket(Ticket ticket) {
        this.ticket = ticket;
        return this;
    }
}
