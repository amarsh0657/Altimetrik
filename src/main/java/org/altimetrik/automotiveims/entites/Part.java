package org.altimetrik.automotiveims.entites;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int quantity;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Suppliers suppliers;
}
