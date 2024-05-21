package com.fastquick.data.repository;

import com.fastquick.data.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

}
