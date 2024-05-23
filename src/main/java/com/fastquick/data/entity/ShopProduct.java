package com.fastquick.data.entity;

import com.fastquick.exception.StockStarvationException;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "connectionId"),
            @JoinColumn(name = "shopId")

    })
    private ShopConnection shopConnection;

    public void setShopConnection(String shopId, Integer connectionId) {
        this.shopConnection = new ShopConnection();
        this.shopConnection.setConnectionId(connectionId);
        this.shopConnection.setShopId(shopId);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;

    public void setMember(Integer memberId) {
        this.member = new Member();
        this.member.setMemberId(memberId);
    }

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

    @Column(name = "shopInsertDate")
    private String shopInsertDate;

    @Column(name = "sellerProductId")
    private Long sellerProductId;

	public void minusStock(int count) {
        if (quantity - count < 0)
            throw new StockStarvationException("재고 이상으로 구매할 수 없습니다");
        quantity -= count;
//        exportAmount += count;
	}

    public void addStock(int buyProductCount) {
        quantity += buyProductCount;
//        exportAmount -= buyProductCount;
    }
}