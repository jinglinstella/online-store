package com.example.demo.repository;

import com.example.demo.domain.Shop;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface ShopRepository extends CrudRepository<Shop, Long> {
	
	@Query("SELECT s FROM Shop s WHERE s.owner.ownerId = :ownerId")
	
	List<Shop> findByOwnerId(Long ownerId);
}
