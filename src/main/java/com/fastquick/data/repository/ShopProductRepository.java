package com.fastquick.data.repository;

import com.fastquick.data.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopProductRepository extends JpaRepository<ShopProduct, Integer> {
    @Query(value = "select s.* from Shop_product s where s.member_id = :memberId" , nativeQuery = true)
    List<ShopProduct> findByMemberId(@Param("memberId") Integer memberId);


    List<ShopProduct> findByProductId(Integer productId);

    @Query("select s from ShopProduct s where s.sellerProductId =:sellerProductId and s.member.memberId=:memberId")
    ShopProduct findByOrderIdAndMemberId(@Param("sellerProductId") Long sellerProductId, @Param("memberId") Integer memberId);
}
