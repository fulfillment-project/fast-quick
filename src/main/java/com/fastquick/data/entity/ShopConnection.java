package com.fastquick.data.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "shopconnection")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ShopConnection extends BaseEntity implements Serializable{

	@Id
    @Column(name = "connectionId")
    private Integer connectionId;

	@Id
    @Column(name = "shopId")
    private String shopId;
    
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @Column(name = "lastProductDate")
    @Temporal(TemporalType.DATE)
    private String lastProductDate;

    @Column(name = "lastOrderDate")
    @Temporal(TemporalType.DATE)
    private String lastOrderDate;
}