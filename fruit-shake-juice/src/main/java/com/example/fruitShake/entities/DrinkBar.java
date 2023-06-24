package com.example.fruitShake.entities;

import java.time.OffsetDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "drinks")
public class DrinkBar {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "drink_uuid", columnDefinition = "BINARY(16)")
    @Id
    private UUID dId;

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
