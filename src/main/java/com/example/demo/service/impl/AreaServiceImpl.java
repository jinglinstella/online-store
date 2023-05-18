package com.example.demo.service.impl;

import com.example.demo.domain.Area;
import com.example.demo.repository.AreaRepository;
import com.example.demo.service.AreaService;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    public AreaServiceImpl(AreaRepository areaRepository){
        this.areaRepository = areaRepository;
    }

    @Override
    public List<Area> findAll(){
        List<Area> areas = new ArrayList<>();
        areaRepository.findAll().forEach(areas::add);
        System.out.println("areaRepo count: "+areaRepository.count());
        System.out.println("Fetched areas: " + areas);
        return areas;

    }

    @Override
    public Area findById(Long aLong) {
        return areaRepository.findById(aLong).orElse(null);
    }

    @Override
    public Area save(Area object) {
        return areaRepository.save(object);
    }

    @Override
    public void delete(Area object) {
        areaRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        areaRepository.deleteById(aLong);
    }
}
