package com.example.fruitShake.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

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

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "drink_cus")
    private String cId;

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
