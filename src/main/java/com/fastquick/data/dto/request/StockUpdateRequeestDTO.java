package com.fastquick.data.dto.request;

import lombok.Data;

@Data
public class StockUpdateRequeestDTO {
    private String shopId;
    private Long sellerProductId;
    private Integer shopProductId;
    private Integer quantity;
}
