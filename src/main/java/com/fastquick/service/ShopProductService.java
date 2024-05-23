package com.fastquick.service;

import com.fastquick.data.dto.request.ShopProductConnectRequestDTO;
import com.fastquick.data.dto.request.ShopProductInquiryRequestDTO;
import com.fastquick.data.dto.response.ShopProductInquiryResponseDTO;
import com.fastquick.data.dto.response.ShopProductListResponseDTO;

import java.util.List;

public interface ShopProductService {
    public List<ShopProductInquiryResponseDTO> inquiryProduct(ShopProductInquiryRequestDTO requestDTO);
    public Integer connectProduct(ShopProductConnectRequestDTO requestDTO);
    public List<ShopProductListResponseDTO> selectShopProduct(Integer productId);

    public void connectShopProduct(ShopProductConnectRequestDTO requestDTO);

    public Integer connectClear(Integer shopProductId);
}
