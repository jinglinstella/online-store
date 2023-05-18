package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

import java.util.*;

@Entity
//@Table(name="Area")
//
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "areaId")

public class Area {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long areaId;

    private String areaName;
    private Integer priority;
    private Date timeCreated;
    private Date timeUpdated;



    @JsonManagedReference
    @OneToMany(
            mappedBy = "area",
            cascade = CascadeType.ALL,
            orphanRemoval = true

    )
//    private Set<Shop> shops = new HashSet<>();
    private List<Shop> shops = new ArrayList<>();

    //constructor
    public Area(){

    }

    public Area(String areaName, Integer priority){
        this.areaName = areaName;
        this.priority = priority;

    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }


    // getter and setters
    public Long getAreaId() {
        return areaId;
    }
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Date getTimeCreated() {
        return timeCreated;
    }
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }
    public Date getTimeUpdated() {
        return timeUpdated;
    }
    public void setTimeUpdated(Date timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", priority=" + priority +
                ", timeCreated=" + timeCreated +
                ", timeUpdated=" + timeUpdated +
//                ", shops=" + shops +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        return areaId != null ? areaId.equals(area.areaId) : area.areaId == null;
    }

    @Override
    public int hashCode() {
        return areaId != null ? areaId.hashCode() : 0;
    }
}
