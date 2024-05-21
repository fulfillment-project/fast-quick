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
public class Storage implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer storageId;

    @Column(nullable = false)
    private Integer shopProductId;

    @Column(nullable = false)
    private Integer connectionId;

    @Column(nullable = false)
    private Integer shopId;

    @Column(nullable = false)
    private Integer memberId;

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
