package com.fastquick.data.dto;

import com.fastquick.data.util.DeliveryStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class OrderDTO {
    private String productName;
    private String time;
    private int count;
    private int price;
    private DeliveryStatus status;
}
