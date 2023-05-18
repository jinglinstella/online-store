package com.example.demo.service;

import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerExecution;

public interface OwnerService {

    Owner getOwnerById(Long userId);

	Owner registerUser(Owner owner);

	Owner login(String email, String password);

	Owner findOwnerByEmail(String email);

//	Owner findOwnerByEmail(String email);


//    OwnerExecution getUserList(Owner userCondition, int pageIndex, int pageSize);
//
//    OwnerExecution modifyOwner(Owner owner); //upsert
}
