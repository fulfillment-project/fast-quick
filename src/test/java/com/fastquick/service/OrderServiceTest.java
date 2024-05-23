package com.fastquick.service;

import com.fastquick.data.repository.ProductOrderDAO;
import com.fastquick.data.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {
	@PersistenceContext
	private EntityManager em;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductOrderDAO productOrderDAO;

}