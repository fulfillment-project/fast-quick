package com.fastquick.service.impl;

import com.fastquick.data.dto.request.ProductWriteRequestDTO;
import com.fastquick.data.dto.request.ShopProductConnectRequestDTO;
import com.fastquick.data.dto.request.ShopProductCreateRequestDTO;
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
    private final ProductServiceImpl productService;

    public ShopProductServiceImpl(ShopProductRepository shopProductRepository, ProductServiceImpl productService) {
        this.shopProductRepository = shopProductRepository;
        this.productService = productService;
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

    @Override
    public Integer connectProduct(ShopProductConnectRequestDTO requestDTO) {
        ProductWriteRequestDTO productWriteRequestDTO = new ProductWriteRequestDTO();
        productWriteRequestDTO.setMemberId(1);
        productWriteRequestDTO.setProductName(requestDTO.getProductName());
        productWriteRequestDTO.setBarcode(requestDTO.getBarcode());
        productWriteRequestDTO.setQuantity(requestDTO.getQuantity());
        Integer productId = this.productService.productInsert(productWriteRequestDTO);

        for(ShopProductCreateRequestDTO shopProduct : requestDTO.getShopProductList()){
            shopProduct.setProductId(productId);
            String url;
            String shopName;
            if(shopProduct.getShopId().equals("A")){
                shopName = "AI_SHOP";
                url = "http://localhost:9080";
            } else {
                shopName = "쿼리마켓";
                url = "http://localhost:9090";
            }
            ShopProduct product = ShopProduct.builder()
                    .quantity(shopProduct.getQuantity())
                    .safeQuantity(0)
                    .shopName(shopName)
                    .image(shopProduct.getImage())
                    .productId(shopProduct.getProductId())
                    .sellerProductName(shopProduct.getSellerProductName())
                    .salePrice(shopProduct.getSalePrice())
                    .shopInsertDate(shopProduct.getInsertDateTime())
                    .sellerProductId(shopProduct.getSellerProductId())
                    .build();
            product.setShopConnection(shopProduct.getShopId(), 258942);
            product.setMember(1);
            this.shopProductRepository.save(product);
            RestTemplateUtil.connectShopProduct(url, "/v2/providers/seller_api/apis/api/v1/marketplace/update/seller-products/change-connect/{sellerProductId}", shopProduct.getSellerProductId());
        }
        return 1;
    }
}
