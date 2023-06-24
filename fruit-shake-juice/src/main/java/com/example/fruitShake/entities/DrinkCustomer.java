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
@Table(name = "customer")
public class DrinkCustomer {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "customer_uuid", columnDefinition = "BINARY(16)")
    @Id
    private UUID cId;

    @Column(name = "name")
    private String cName;

    @Column(name = "phone_No")
    private String cPhoneNo;
    
    @Column(name = "address")
    private String cAddress;

	@Column(name = "created_On", nullable = false)
	private OffsetDateTime createdOn;

    @Column(name = "updated_On", nullable = false)
	private OffsetDateTime updatedOn;
}
