package com.fastquick.data.entity;

import lombok.*;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.PrePersist;


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
    
    @Column(name = "safeQuantity", nullable = false)
    private Integer safeQuantity;

    @Column(name = "import", nullable = false)
    private Integer importAmount;

    @Column(name = "export", nullable = false)
    private Integer exportAmount;

    @Column(name = "image")
    private String image;

    @Column(name = "barcode", unique = true, nullable = false)
    private Long barcode;

    
    @PrePersist
    public void prePersist() {
        // barcode 필드가 비어있으면 랜덤한 6자리 숫자를 생성하여 할당
        if (barcode == null) {
            this.barcode = generateRandomBarcode();
        }
    }

    private Long generateRandomBarcode() {
        // 100000부터 999999까지의 랜덤값 생성
        return (long) (new Random().nextInt(900000) + 100000);
    }
}
