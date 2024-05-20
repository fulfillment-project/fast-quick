package com.fastquick.data.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Product extends BaseEntity {

   @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "import", nullable = false)
    private Integer importAmount;

    @Column(name = "export", nullable = false)
    private Integer exportAmount;

    @Column(name = "image")
    private String image;

    @Column(name = "barcode", unique = true, nullable = false)
    private Long barcode;
}