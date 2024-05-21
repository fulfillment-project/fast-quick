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
<<<<<<< HEAD:src/main/java/com/fastquick/data/entity/StorageRetrieval.java
public class StorageRetrieval implements Serializable {
=======
public class Storage extends BaseEntity implements Serializable {
>>>>>>> 52e6a36db1d0c1566d70733fd8f4054d6199b2b4:src/main/java/com/fastquick/data/entity/Storage.java

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storageId;

    @ManyToOne
    @JoinColumn(name = "shopProductId", nullable = false)
    private ShopProduct shopProduct;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "connectionId", referencedColumnName = "connectionId"),
        @JoinColumn(name = "shopId", referencedColumnName = "shopId")
    })
    private ShopConnection shopConnection;

    @ManyToOne
    @JoinColumn(name = "memberId", nullable = false)
    private Member member;

    @Column
    private Integer productOrderId;

    @Column
    private String division;

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
}