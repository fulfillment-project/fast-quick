package com.fastquick.service;

import com.fastquick.data.dto.request.ShopProductInquiryRequestDTO;
import com.fastquick.data.dto.response.ShopProductInquiryResponseDTO;

import java.util.List;

public interface ShopProductService {
    public List<ShopProductInquiryResponseDTO> inquiryProduct(ShopProductInquiryRequestDTO requestDTO);
}
