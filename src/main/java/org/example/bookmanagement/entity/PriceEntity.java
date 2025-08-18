package org.example.bookmanagement.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "prices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @OneToOne(mappedBy = "price")
    private BookEntity book;
}
