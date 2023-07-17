package com.example.fruitShake.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.GeneratedValue;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class Users {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "user_uuid", columnDefinition = "BINARY(16)")
    @Id
    private UUID userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "mobile_no")
    private String mobileNo;

    @Column(name = "address")
    private String address;

    @Column(name = "created_on", nullable = false)
    private OffsetDateTime createdOn;

    @Column(name = "updated_On", nullable = false)
    private OffsetDateTime updatedOn;

//    @OneToMany(mappedBy = "user")
//    private ArrayList<BookUsers> bookUsers = new ArrayList<>();

}