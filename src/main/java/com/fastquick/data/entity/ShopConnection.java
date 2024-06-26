package com.fastquick.data.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "shopConnection")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ShopConnectionId.class)
public class ShopConnection extends BaseEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    private String lastProductDate;

    @Column(name = "lastOrderDate")
    private String lastOrderDate;
}