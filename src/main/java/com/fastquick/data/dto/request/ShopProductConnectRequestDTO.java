package com.fastquick.data.dto.request;

import com.fastquick.data.entity.Member;
import lombok.Data;

import java.util.List;

@Data
public class ShopProductConnectRequestDTO {
    private String productName;
    private Long barcode;
    private Integer quantity;
    private Integer memberId;
    private List<ShopProductCreateRequestDTO> shopProductList;
}
