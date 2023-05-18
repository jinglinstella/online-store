package com.example.demo.repository;

//import static org.junit.Assert.assertEquals;

import java.util.Date;

//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
import com.example.demo.domain.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository personInfoRepo;

    @Test
    public void testInsertOwner() throws Exception{
        Owner personInfo = new Owner();
        personInfo.setName("new user 123");
        personInfo.setGender("F");
//        personInfo.setUserType(1);
        personInfo.setTimeCreated(new Date());
        personInfo.setTimeUpdated(new Date());
//        personInfo.setEnableStatus(1);
        personInfoRepo.save(personInfo);
        assertEquals(2, personInfoRepo.count());

    }

    @Test
    public void testBQueryUserById() {
        long userId = 3;
        Owner person = personInfoRepo.findById(userId).orElse(null);
        System.out.println(person.getName());
    }

}
