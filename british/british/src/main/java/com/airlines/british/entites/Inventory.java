package com.airlines.british.entites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="inventory")
public class Inventory {
	
	@Id
	@Column(name = "inventoryId", nullable = false, unique = true)
	private String inventoryId;
	
	private int count;
    
}
