package com.fastquick.data.repository;

import com.fastquick.data.entity.ShopConnection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShopConnectionRepository extends JpaRepository<ShopConnection, Integer> {
	@Query("select s from ShopConnection s where s.member.memberId =: id")
	List<ShopConnection> findByMemberId(int id);
}
