package com.example.demo.service.impl;

import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerExecution;
import com.example.demo.repository.OwnerRepository;
import com.example.demo.service.OwnerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;
    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Owner getOwnerById(Long userId){

        return ownerRepository.findById(userId).orElse(null);
    }
    
    @Override
    public Owner login(String email, String password) {
    	//TODO
    	return new Owner(email, password);
    }
    
    @Override
    public Owner registerUser(Owner user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setTimeCreated(new Date());
        user.setTimeUpdated(new Date());
        return ownerRepository.save(user);
    }
    
    @Override
    public Owner findOwnerByEmail(String email) {
        return ownerRepository.findByEmail(email);
    }

//    @Override
//    public OwnerExecution getUserList(Owner userCondition, int pageIndex, int pageSize) {
//        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
//        List<User> userList = userDao.queryUserList(userCondition, rowIndex, pageSize);
//        int count = userDao.queryUserCount(userCondition);
//        UserExecution ue = new UserExecution();
//        if (userList != null) {
//            ue.setUserList(userList);
//            ue.setCount(count);
//        } else {
//            ue.setState(UserStateEnum.INNER_ERROR.getState());
//        }
//        return ue;
//    }


}
