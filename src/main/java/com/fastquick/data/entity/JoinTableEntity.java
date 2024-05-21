package com.fastquick.data.entity;


import javax.persistence.*;

@Entity
public class JoinTableEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// EntityA와의 관계 설정
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "connectionId")
	 * 
	 * @JoinColumn(name = "shopId") private ShopConnection shopConnection;
	 */
	
	 @ManyToOne
	    @JoinColumn(name = "productOrderId")
	    private ProductOrder productOrder;
	

	// 다른 필드들

	// 생성자, 게터 및 세터 등 필요한 메서드들
}

