package com.fastquick.service;

import java.util.List;

import com.fastquick.data.dto.response.ProductListResponseDTO;

public interface ProductService {
    public List<ProductListResponseDTO> productList(String productName, Integer page);
}
