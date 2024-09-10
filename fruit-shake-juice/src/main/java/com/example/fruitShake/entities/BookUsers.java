package com.example.fruitShake.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.GenericGenerator;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books_user")
public class BookUsers {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "book_user")
    private String Id;

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
