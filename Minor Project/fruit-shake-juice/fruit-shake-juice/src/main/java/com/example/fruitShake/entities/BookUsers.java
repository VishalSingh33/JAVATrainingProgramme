package com.example.fruitShake.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.GeneratedValue;
import lombok.NoArgsConstructor;
import com.example.fruitShake.dto.BookStatus;
import jakarta.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books_user")
public class BookUsers {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "book_user_uuid", columnDefinition = "BINARY(16)")
    private UUID Id;
   
    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "issued_date")
    private OffsetDateTime issuedDate;

    @Column(name = "return_date")
    private OffsetDateTime returnDate;

    @Column(name = "book_status")
    private String status;

}
