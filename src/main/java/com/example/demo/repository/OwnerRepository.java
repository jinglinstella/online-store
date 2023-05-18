package com.example.demo.repository;

import com.example.demo.domain.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
	
	Owner findByEmail(String email);
}
