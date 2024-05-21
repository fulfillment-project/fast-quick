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
    private Integer productId;

    @ManyToOne
    @JoinColumn(name = "memberId" , nullable = false)
    private Member memberId;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "safeQuantity")
    private Integer safeQuantity;

    @Column(name = "import")
    private Integer importAmount;

    @Column(name = "export")
    private Integer exportAmount;

    @Column(name = "image")
    private String image;

    @Column(name = "barcode", unique = true, nullable = false)
    private Long barcode;

    
    @PrePersist
    public void prePersist() {
    	
    	// quantity가 null이면 0으로 설정
        if (quantity == null) {
            this.quantity = 0;
        }

        // importAmount가 null이면 0으로 설정
        if (importAmount == null) {
            this.importAmount = 0;
        }

        // exportAmount가 null이면 0으로 설정
        if (exportAmount == null) {
            this.exportAmount = 0;
        }

        // safeQuantity가 null이면 0으로 설정
        if (safeQuantity == null) {
            this.safeQuantity = 0;
        }
        // barcode 필드가 비어있으면 랜덤한 6자리 숫자를 생성하여 할당
        if (barcode == null) {
            this.barcode = generateRandomBarcode();
        }
    }

    private Long generateRandomBarcode() {
        // 10000000부터 99999999까지의 랜덤값 생성
        return (long) (new Random().nextInt(90000000) + 10000000);
    }
}
