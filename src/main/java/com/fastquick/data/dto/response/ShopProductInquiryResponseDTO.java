package com.fastquick.data.dto.response;

import lombok.Data;

@Data
public class ShopProductInquiryResponseDTO {
    private Long sellerProductId;
    private String sellerProductName;
    private String content;
    private Integer vendorId;
    private Integer salePrice;
    private Integer maxBuyCount;
    private Integer quantity;
    private String brand;
    private String image;
    private Integer deliveryCharge;
    private String shopId;
    private String insertDateTime;
    private String updateDateTime;
}
