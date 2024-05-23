package com.fastquick.data.dto.request;

import lombok.*;

@Getter
@Setter
public class StockEditRequestDTO {
    
    @NonNull 
    private Integer safeQuantity;
    
    @NonNull 
    private Integer tempQuantity;
    
}
