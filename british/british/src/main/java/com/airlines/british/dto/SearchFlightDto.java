package com.airlines.british.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchFlightDto {

    private String origin;
    private String destination;
    private String dateTime;
    private String startTime;
    private String endTime;

}
