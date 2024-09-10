package com.example.fruitShake.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class Users {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "users_id")
    private String userId;

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