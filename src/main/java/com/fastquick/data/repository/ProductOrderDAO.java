package com.fastquick.data.repository;

import com.fastquick.data.entity.ProductOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ProductOrderDAO {
	private final EntityManager em;

	public void save(ProductOrder productOrder) {
		em.persist(productOrder);
	}

	public void findOne(int id) {
		em.find(ProductOrder.class, id);
	}
}
