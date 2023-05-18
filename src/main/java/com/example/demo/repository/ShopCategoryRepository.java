package com.example.demo.repository;

import com.example.demo.domain.Owner;
import com.example.demo.domain.ShopCategory;
import org.springframework.data.repository.CrudRepository;

public interface ShopCategoryRepository extends CrudRepository<ShopCategory, Long> {
}
