package org.example.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user; // one user can borrow

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id", referencedColumnName = "id")
    private PriceEntity price;
}

