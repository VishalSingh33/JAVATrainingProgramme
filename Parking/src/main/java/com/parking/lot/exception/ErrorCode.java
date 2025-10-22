package com.parking.lot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an application-level error code with HTTP-style metadata.
 * Mirrors Go's exceptions.Error structure.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorCode {
    private int statusCode;
    private String errorMessage;
    private String errorCode;

    // ---------------------- Static predefined errors ----------------------

    public static final ErrorCode ERR_GENERATING_TICKET_FAILURE = new ErrorCode(
            500,
            "Some error occurred while generating ticket.",
            "PL-001-F"
    );

    public static final ErrorCode ERR_GENERATING_TICKET_SUCCESS = new ErrorCode(
            200,
            "Ticket generated successfully.",
            "PL-001-S"
    );

    public static final ErrorCode ERR_NO_TICKET_FOUND = new ErrorCode(
            500,
            "No ticket found.",
            "PL-002-F"
    );

    public static final ErrorCode ERR_GENERATING_BILL_FAILURE = new ErrorCode(
            500,
            "Error generating bill.",
            "PL-003-F"
    );

    public static final ErrorCode ERR_GENERATING_BILL_SUCCESS = new ErrorCode(
            200,
            "Bill generated successfully.",
            "PL-003-S"
    );
}
