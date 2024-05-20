package com.fastquick.data.dto.request;

import lombok.*;

@Getter
@Setter
public class ProductWriteRequestDTO {
	
    private Long barcode;
	
	@NonNull
    private Integer export;
	
	
    private String image;
    
    @NonNull
    private Integer importAmount;
    
    @NonNull
    private String productName;
    
    @NonNull
    private Integer quantity;
    
    @NonNull
    private Integer memberId;
}
