package com.fastquick.data.repository;

import com.fastquick.data.dto.OrderDTO;
import com.fastquick.data.entity.ProductOrder;
import com.fastquick.data.util.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer> {

	@Query("select p from ProductOrder p where p.status =:status")
	List<ProductOrder> findAllByStatus(@Param("status") DeliveryStatus status);

	@Query("select p from ProductOrder p where p.status =:status and p.member.memberId =:id")
	List<ProductOrder> findAllByStatusAndMemberId(@Param("status") DeliveryStatus status, @Param("id") int id);

}
