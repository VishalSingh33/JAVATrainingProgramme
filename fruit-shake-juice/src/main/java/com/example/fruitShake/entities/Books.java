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
@Table(name = "books")
public class Books {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "book_id")
    private String bookId;

    // @NotNull
    @Column(name = "book_name")
    private String bookName;
    
    @Column(name = "status")
    private String status;

	@Column(name = "created_On", nullable = false)
	private OffsetDateTime createdOn;

    @Column(name = "updated_On", nullable = false)
	private OffsetDateTime updatedOn;
}
