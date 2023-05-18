package com.example.demo.domain;
import javax.persistence.*;

import java.util.*;

@Entity
public class Owner {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long ownerId;

    @OneToMany(
            mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
//    @JoinColumn(name="user_id")
//    private Set<Shop> shops = new HashSet<>();
    private List<Shop> shops = new ArrayList<>();

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    private String name;
//    private String profileImg;
    private String email;
    private String gender;
//    private Integer enableStatus;// 0: not active, 1: active
//    private Integer userType; // 1: customer, 2: merchant, 3: admin
    private Date timeCreated;
    private Date timeUpdated;
    private String password;
    
    

    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Owner(){

    }
	
	public Owner(String email, String password) {
		this.email = email;
		this.password = password;
		
	}

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long userId) {
        this.ownerId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getProfileImg() {
//        return profileImg;
//    }
//
//    public void setProfileImg(String profileImg) {
//        this.profileImg = profileImg;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

//    public Integer getEnableStatus() {
//        return enableStatus;
//    }
//
//    public void setEnableStatus(Integer enableStatus) {
//        this.enableStatus = enableStatus;
//    }
//
//    public Integer getUserType() {
//        return userType;
//    }
//
//    public void setUserType(Integer userType) {
//        this.userType = userType;
//    }

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
}
