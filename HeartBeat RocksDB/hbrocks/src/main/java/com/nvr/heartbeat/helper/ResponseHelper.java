package com.nvr.heartbeat.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseHelper {

	private static Logger log = LoggerFactory.getLogger(ResponseHelper.class);

	public static <T> Response<T> getSuccessResponse(T obj) {
		log.trace("getSuccessResponse({}) start", obj);
		Response<T> response = new Response<>();
		response.setData(obj);
		response.setStatus(ResponseCode.SUCCESS.getDesc());
		response.setStatusCode(ResponseCode.SUCCESS.getCode());
		response.setMessage(ResponseCode.SUCCESS.getMessage());
//		response.setDesc(ResponseCode.SUCCESS.getDesc());
		log.trace("getSuccessResponse() exit. Response: {}", response);
		return response;
	}

	public static <T> Response<T> getErrorResponse(ResponseCode responseCode) {
		log.trace("getErrorResponse({}) start", responseCode);
		Response<T> response = new Response<>();

		response.setStatus(ResponseCode.FAIL.getDesc());
//		response.setDesc(responseCode.getDesc());
		response.setStatusCode(responseCode.getCode());
		response.setMessage(responseCode.getMessage());

		log.trace("getErrorResponse() exit. Response: {}", response);
		return response;
	}

	public static <T> Response<T> getErrorResponse(ResponseCode responseCode, String exceptionMessage) {
		log.trace("getErrorResponse({}, {}) start", responseCode, exceptionMessage);
		final Response<T> errorResponse = getErrorResponse(responseCode);
		errorResponse.setMessage(exceptionMessage);
		log.trace("getErrorResponse() exit. Response: {}", errorResponse);
		return errorResponse;
	}

}
