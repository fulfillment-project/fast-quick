package com.fastquick.data.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fastquick.data.dto.request.ShopProductInquiryRequestDTO;
import com.fastquick.data.dto.response.ShopProductInquiryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RestTemplateUtil {

    public static List<ShopProductInquiryResponseDTO> inquiryShopProductList(String url, String path, ShopProductInquiryRequestDTO requestDTO){
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .path(path)
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> result = restTemplate.postForEntity(uri, requestDTO, Map.class);
        List<LinkedHashMap> data = (List<LinkedHashMap>) result.getBody().get("data");
        ObjectMapper mapper = new ObjectMapper();
        CollectionType listType = mapper.getTypeFactory().constructCollectionType(List.class, ShopProductInquiryResponseDTO.class);
        return mapper.convertValue(data, listType);
    }
}
