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
@Table(name = "user")
public class User {

	@Id
	@Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private long mobileNumber;

    @Column(name = "gender")
    private String gender;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;
    
}
