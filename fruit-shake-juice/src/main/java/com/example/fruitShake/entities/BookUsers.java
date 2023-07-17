package com.example.fruitShake.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import jakarta.persistence.GeneratedValue;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books_user")
public class BookUsers {

    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "book_user_uuid", columnDefinition = "BINARY(16)")
    @Id
    private UUID Id;

//    @ManyToOne
//    @JoinColumn(name = "book_id", nullable = false)
//    private Books bookId;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private Users userId;

    @Column(name = "user_id", nullable = false)
    private String userId; ;

    @JoinColumn(name = "book_id", nullable = false)
    private List<String> bookIdList;

    @Column(name = "issued_date")
    private OffsetDateTime issuedDate;

    @Column(name = "return_date")
    private OffsetDateTime returnDate;

    @Column(name = "book_status")
    private String status;

}
