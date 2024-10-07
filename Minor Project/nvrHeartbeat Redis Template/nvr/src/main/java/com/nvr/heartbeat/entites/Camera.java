package com.nvr.heartbeat.entites;

public class Camera {

	private String cctvId;

	private String status;

	public String getCctvId() {
		return cctvId;
	}

	public void setCctvId(String cctvId) {
		this.cctvId = cctvId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Camera{" + "cctvId='" + cctvId + '\'' + ", status='" + status + '\'' + '}';
	}

}