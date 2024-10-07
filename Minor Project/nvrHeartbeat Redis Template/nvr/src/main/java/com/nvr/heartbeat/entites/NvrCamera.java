package com.nvr.heartbeat.entites;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NvrCamera implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nvrId;

	private String status;

	@JsonProperty("cameras")
	private List<Camera> cameraList;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public String getNvrId() {
		return nvrId;
	}

	public void setNvrId(String nvrId) {
		this.nvrId = nvrId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Camera> getCameraList() {
		return cameraList;
	}

	public void setCameraList(List<Camera> cameraList) {
		this.cameraList = cameraList;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "NvrCamera [nvrId=" + nvrId + ", status=" + status + ", cameraList=" + cameraList + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + ", getNvrId()=" + getNvrId() + ", getStatus()=" + getStatus()
				+ ", getCameraList()=" + getCameraList() + ", getCreatedAt()=" + getCreatedAt() + ", getUpdatedAt()="
				+ getUpdatedAt() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
