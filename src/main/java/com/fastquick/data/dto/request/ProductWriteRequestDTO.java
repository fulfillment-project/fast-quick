package com.fastquick.data.dto.request;

import com.fastquick.data.entity.Member;

import lombok.*;

@Getter
@Setter
public class ProductWriteRequestDTO {
	
    private Long barcode;
	
    private String image;
    
    @NonNull
    private String productName;
    
    private Integer safeQuantity;
    
    private Integer quantity;
    
    @NonNull
    private Integer memberId;
}
