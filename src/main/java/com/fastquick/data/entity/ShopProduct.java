package com.fastquick.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "shopProduct")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ShopProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopProductId")
    private Integer shopProductId;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "connectionId"),
        @JoinColumn(name = "shopId")
    })
    private ShopConnection shopConnection;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(name = "quantity")
    private Integer quantity;
    
    @Column(name = "safeQuantity", nullable = false)
    private Integer safeQuantity;

    @Column(name = "import")
    private Integer importAmount;

    @Column(name = "export")
    private Integer exportAmount;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "image")
    private String image;

    @Column(name = "productId")
    private Integer productId;

    @Column(name = "sellerProductName")
    private String sellerProductName;

    @Column(name = "salePrice")
    private Integer salePrice;
}