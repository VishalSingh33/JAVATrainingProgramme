package com.skypro.broadband.entities;

import java.time.LocalDateTime;
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
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_type")
    private String userType;

    @Column
    private String message;

    @Column
    private String topic;

    @Column
    private String link;

    @Column(name = "read_flag")
    private String readFlag;

    @Column(name = "triggered_by")
    private String triggeredBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
