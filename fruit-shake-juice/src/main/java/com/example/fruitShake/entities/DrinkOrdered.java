package com.example.fruitShake.entities;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.deser.std.UUIDDeserializer;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "drink_ordered")
public class DrinkOrdered {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "bc_uuid", columnDefinition = "BINARY(16)")
    @Id
    private UUID oId;

    // @ManyToOne
    // @JoinColumn(name = "dId", nullable = false)
    // private DrinkBar dId;

    // @ManyToOne
    // @JoinColumn(name = "cId", nullable = false)
    // private DrinkCustomer cId;

    @Column(name = "customer_uuid", nullable = false)
    private String cId; ;

    @Column(name = "drink_Id", nullable = false)
    private List<String> dIdList;

//    @Column(name = "quantity", nullable = false)
//    private String quantity;

    @Column(name = "total_amount", nullable = false)
    private String totalAmount;

    @Column(name = "payment_mode", nullable = false)
    private String paymentMode;

    @Column(name = "created_On", nullable = false)
    private OffsetDateTime createdOn;

    @Column(name = "updated_On", nullable = false)
    private OffsetDateTime updatedOn;
}
