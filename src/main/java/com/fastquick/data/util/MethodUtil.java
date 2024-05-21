package com.fastquick.data.util;

import com.fastquick.data.dto.response.ShopProductInquiryResponseDTO;
import com.fastquick.data.entity.ShopProduct;

import java.util.List;
import java.util.Objects;

public class MethodUtil {

    public static void filterList(List<ShopProduct> dbList, List<ShopProductInquiryResponseDTO> apiList, List<ShopProductInquiryResponseDTO> resultList, String shopId){
        addMatchingApis(apiList, dbList, resultList, shopId);
    }

    private static void addMatchingApis(List<ShopProductInquiryResponseDTO> apiList, List<ShopProduct> dbList, List<ShopProductInquiryResponseDTO> resultList, String shopId) {
        for (ShopProductInquiryResponseDTO api : apiList) {
            boolean matchFound = false;
            for (ShopProduct db : dbList) {
                if (Objects.equals(db.getSellerProductId(), api.getSellerProductId()) && Objects.equals(db.getShopConnection().getShopId(), shopId)) {
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                api.setShopId(shopId);
                resultList.add(api);
            }
        }
    }
}
