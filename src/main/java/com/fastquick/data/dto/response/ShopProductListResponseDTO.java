package com.fastquick.data.dto.response;

import com.fastquick.data.entity.Product;
import com.fastquick.data.entity.ShopProduct;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopProductListResponseDTO {
    private Long sellerProductId;
    private String sellerProductName;
    private Integer shopProductId;
    private String content;
    private Integer vendorId;
    private Integer salePrice;
    private Integer maxBuyCount;
    private Integer quantity;
    private String brand;
    private String image;
    private Integer deliveryCharge;
    private String shopId;
    private String shopName;
    private String insertDateTime;
    private String updateDateTime;

    public ShopProductListResponseDTO fromProduct(ShopProduct product) {
        this.sellerProductId = product.getSellerProductId();
        this.sellerProductName = product.getSellerProductName();
        this.salePrice = product.getSalePrice();
        this.shopName = product.getShopName();
        this.shopId = product.getShopName().equals("AI_SHOP") ? "A" : "B";
        this.quantity = product.getQuantity();
        this.shopProductId = product.getShopProductId();
        return this;
    }

    public static ShopProductListResponseDTO ShopProductFactory(ShopProduct product) {
        ShopProductListResponseDTO productDetailResponseDTO = new ShopProductListResponseDTO();
        productDetailResponseDTO.fromProduct(product);

        return productDetailResponseDTO;
    }
}
