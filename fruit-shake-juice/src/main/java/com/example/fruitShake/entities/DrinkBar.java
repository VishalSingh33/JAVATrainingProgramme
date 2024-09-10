package com.example.fruitShake.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize
@Entity
@Builder
@Table(name = "drinks")
public class DrinkBar {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "drink_bar")
    private String dId;

    @Column(name = "type")
    private String dType;

    @Column(name = "name")
    private String dName;
    
    @Column(name = "status")
    private String dStatus;

	@Column(name = "created_On", nullable = false)
	private OffsetDateTime createdOn;

    @Column(name = "updated_On", nullable = false)
	private OffsetDateTime updatedOn;
}
