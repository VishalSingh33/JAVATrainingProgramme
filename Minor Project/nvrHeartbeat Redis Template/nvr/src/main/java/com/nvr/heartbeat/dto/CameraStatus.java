package com.nvr.heartbeat.dto;

import java.util.List;

public class CameraStatus {

	private String nvrId;
	private List<CameraDto> cameras;

	public CameraStatus(String nvrId, List<CameraDto> cameras) {
		super();
		this.nvrId = nvrId;
		this.cameras = cameras;
	}

	public String getNvrId() {
		return nvrId;
	}

	public void setNvrId(String nvrId) {
		this.nvrId = nvrId;
	}

	public List<CameraDto> getCameras() {
		return cameras;
	}

	public void setCameras(List<CameraDto> cameras) {
		this.cameras = cameras;
	}

}