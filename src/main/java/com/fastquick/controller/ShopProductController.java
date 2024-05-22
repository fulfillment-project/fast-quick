package com.fastquick.controller;

import com.fastquick.data.dto.request.ShopProductConnectRequestDTO;
import com.fastquick.data.dto.request.ShopProductInquiryRequestDTO;
import com.fastquick.data.dto.response.ShopProductInquiryResponseDTO;
import com.fastquick.service.impl.ShopProductServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop-product")
public class ShopProductController {

    private final ShopProductServiceImpl shopProductService;

    public ShopProductController(ShopProductServiceImpl shopProductService) {
        this.shopProductService = shopProductService;
    }

    @ResponseBody
    @PostMapping("/inquiryProduct")
    public ResponseEntity<Map<String, Object>> inquiryProduct(@RequestBody(required = false) ShopProductInquiryRequestDTO requestDTO){
        System.out.println(requestDTO.getToDate());
        List<ShopProductInquiryResponseDTO> productList = this.shopProductService.inquiryProduct(requestDTO);
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("code", "SUCCESS");
        result.put("data", productList);
        return ResponseEntity.ok().body(result);
    }

    @ResponseBody
    @PostMapping("/connect-product")
    public ResponseEntity<Map<String, Object>> connectProject(@RequestBody(required = false)ShopProductConnectRequestDTO requestDTO){
        this.shopProductService.connectProduct(requestDTO);
        return null;
    }

    @ResponseBody
    @PostMapping("/connect-shop-product")
    public ResponseEntity<Map<String, Object>> connectShopProduct(@RequestBody(required = false)ShopProductConnectRequestDTO requestDTO){
        this.shopProductService.connectShopProduct(requestDTO);
        return null;
    }
}
