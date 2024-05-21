package com.fastquick.data.dto.request;

import lombok.Data;

@Data
public class ShopProductInquiryRequestDTO {
    private Integer vendorId = 258942;
    private String fromDate = "1900-01-01";
    private String toDate = "2024-05-21";
    private String keyword;
}
