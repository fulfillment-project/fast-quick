package com.fastquick.data.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
<<<<<<< HEAD
@Table(name = "shopconnection")
@Getter
@Setter
=======
@Table(name = "shopConnection")
>>>>>>> 52e6a36db1d0c1566d70733fd8f4054d6199b2b4
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopConnection extends BaseEntity implements Serializable {

    @EmbeddedId
    private ShopConnectionId shopConnectionId;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(name = "lastProductDate")
    private String lastProductDate;

    @Column(name = "lastOrderDate")
    private String lastOrderDate;
}