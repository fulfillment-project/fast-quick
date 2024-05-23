package com.fastquick.data.entity;

import javax.persistence.*;
import java.io.Serializable;
import lombok.*;

@Entity
@Table(name = "storageRetrieval")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class StorageRetrieval extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storageId;
    
    private Integer productId;
    
    private String shopId;
    
    private Integer connectionId;
    
    private Integer shopProductId;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column
    private Integer productOrderId;

    @Column
    private String division;
    
    @Column
    private Integer quantity;

    @Column
    private Integer count;

    @Column
    private String productName;

    @Column
    private String warehouse;

    @Column
    private String field;

    @Column
    private String zipcode;

    @Column
    private String address;

    @Column
    private String addressDetail;

    @Column
    private String bigo;

    public static StorageRetrieval createStorageRetrieval(Member member, ProductOrder productOrder) {
        return StorageRetrieval.builder()
                .address(productOrder.getAddress())
                .addressDetail(productOrder.getAddressDetail())
                .bigo(productOrder.getCustomMemo())
                .productOrderId(productOrder.getId())
                .division("2")
                .quantity(productOrder.getShopProduct().getQuantity())
                .count(productOrder.getBuyProductCount())
                .productName(productOrder.getOrderName())
                .zipcode(productOrder.getZipCode())
                .build();
    }
    
}