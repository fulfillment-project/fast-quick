package com.fastquick.data.dto.response;

import com.fastquick.data.util.DeliveryStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProductOrderResponse {
	private Integer productOrderId;
	private Long sellerProductId;
	private String sellerProductName;
	private Integer customId;
	private String customName;
	private Integer vendorId;
	private Integer buyProductCount;
	private Integer salePrice;
	private Integer totalPrice;
	private String phoneNumber;
	private String zipCode;
	private String address;
	private String addressDetail;
	private String memo;
	private String deliveryStatus;
	private String insertDateTime;
	private String updateDateTime;
	private DeliveryStatus status;
}
