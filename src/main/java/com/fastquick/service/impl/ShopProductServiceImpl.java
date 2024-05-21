package com.fastquick.service.impl;

import com.fastquick.data.dto.request.ShopProductInquiryRequestDTO;
import com.fastquick.data.dto.response.ShopProductInquiryResponseDTO;
import com.fastquick.data.entity.ShopProduct;
import com.fastquick.data.repository.ShopProductRepository;
import com.fastquick.data.util.MethodUtil;
import com.fastquick.data.util.RestTemplateUtil;
import com.fastquick.service.ShopProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopProductServiceImpl implements ShopProductService {

    private final ShopProductRepository shopProductRepository;

    public ShopProductServiceImpl(ShopProductRepository shopProductRepository) {
        this.shopProductRepository = shopProductRepository;
    }

    @Override
    public List<ShopProductInquiryResponseDTO> inquiryProduct(ShopProductInquiryRequestDTO requestDTO) {
        List<ShopProductInquiryResponseDTO> resultList = new ArrayList<>();
        List<ShopProductInquiryResponseDTO> aShopProductList = RestTemplateUtil.inquiryShopProductList("http://localhost:9080","/v2/providers/seller_api/apis/api/v1/marketplace/select/seller-products", requestDTO);
        List<ShopProductInquiryResponseDTO> bShopProductList = RestTemplateUtil.inquiryShopProductList("http://localhost:9090","/v2/providers/seller_api/apis/api/v1/marketplace/select/seller-products", requestDTO);
        List<ShopProduct> productsList =  this.shopProductRepository.findByMemberId(1);
        MethodUtil.filterList(productsList, aShopProductList, resultList, "A");
        MethodUtil.filterList(productsList, bShopProductList, resultList, "B");

        return resultList;
    }
}
