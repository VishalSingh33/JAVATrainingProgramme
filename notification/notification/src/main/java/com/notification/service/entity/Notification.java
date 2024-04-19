package com.notification.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notification")
public class Notification {

	@Id
    @Column(name = "id", nullable = false, unique = true)
    private String id;
	
	@Column
	private String message;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "user_id_fk"))
	private User user;

}
