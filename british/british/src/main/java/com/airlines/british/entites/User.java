package com.airlines.british.entites;

import java.time.LocalDateTime;

import com.airlines.british.dto.Gender;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
// import jakarta.validation.constraints.Pattern;

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

    @Column(name = "name", nullable = false)
    private String fullName;

    @Column(name = "username", nullable = false)
    private String userName;

    // @Pattern(regexp = "(^[\\w!#$%&’*+/=?`{|}~^-]+(?:.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+.)+([a-zA-Z]{2,6})$)*", message = "Please enter valid emailId")
    @Column(name = "email", nullable = false)
    private String email;

    // @Pattern(regexp = "^([9876]{1})(\\d{9})", message = "Please enter valid mobile number")
    @Column(name = "mobile", nullable = false)
    private long mobileNumber;

    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "created", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updatedAt;

}
