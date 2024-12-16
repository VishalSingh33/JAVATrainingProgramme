package com.nvr.heartbeat.helper;

public enum ResponseCode {

	FAIL(500, "FAIL", "Internal Server Error"), NO_TAGS_INFO_PASSED(400, "No tags info passed...", "Bad request"),
	DATA_NOT_FOUND(404, "Data not found", "Failure"), REQUEST_TIMEOUT(408, "Request Timeout", "Time Limit Exceeded"),
	Error_occured(400, "Error occured", "Error Occured"), SUCCESS(200, "SUCCESS", "SUCCESS"),

	DATA_NULL(1018, "Provided data is null", "Provided data is null"),
	SERVER_ERROR(503, "Could not connect to server", "Could not connect to server");

	// public static final Map<String, ResponseCode> FIELD_NULL_ERROR_MAP =
	// createFieldNullErrorMap();
	private final int code;
	private final String desc;
	private final String message;

	ResponseCode(int code, String desc, String message) {

		this.code = code;
		this.desc = desc;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public String getMessage() {
		return message;
	}

}
