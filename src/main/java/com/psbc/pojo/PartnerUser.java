package com.psbc.pojo;

public class PartnerUser {
    private Long partnerId;

    private String type;

    private String oldCustomer;

    private String workUnitType;
    private String workUnitName;

    private String userName;

    private String phoneNumber;

    private String shopName;
    private String shopAddress;

    private String area;
    
    private String posterType;

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOldCustomer() {
        return oldCustomer;
    }

    public void setOldCustomer(String oldCustomer) {
        this.oldCustomer = oldCustomer;
    }

    public String getWorkUnitType() {
        return workUnitType;
    }

    public void setWorkUnitType(String workUnitType) {
        this.workUnitType = workUnitType;
    }

    public String getWorkUnitName() {
        return workUnitName;
    }

    public void setWorkUnitName(String workUnitName) {
        this.workUnitName = workUnitName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPosterType() {
        return posterType;
    }

    public void setPosterType(String posterType) {
        this.posterType = posterType;
    }
}