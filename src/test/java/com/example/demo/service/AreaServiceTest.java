package com.example.demo.service;

//import static org.junit.Assert.assertEquals;

import java.util.List;

//import org.junit.Test;
import com.example.demo.domain.Area;
import com.example.demo.repository.AreaRepository;
import com.example.demo.service.impl.AreaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
public class AreaServiceTest {

//    @Mock
//    AreaRepository areaRepository;

    Area area;


//    @InjectMocks
//    AreaServiceImpl areaServiceImpl;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private AreaService areaService;

    @BeforeEach
    void setup() {
        // Load your test data here
        Area area = new Area("TestArea", 1);
        areaRepository.save(area);
    }

    @Test
    void testFindAll() {

        List<Area> areas = areaService.findAll();
        assertEquals(2, areas.size());

    }




}
