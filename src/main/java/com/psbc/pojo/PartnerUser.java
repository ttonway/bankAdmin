package com.psbc.pojo;

public class PartnerUser {
    private Long partnerId;

    private String partnerType;

    private String oldCustomer;

    private String workUnitType;
    private String workUnitName;

    private String userName;

    private String phoneNumber;

    private String shopName;
    private String shopAddress;

    private String bank;

    private String posterType;
    private String posterFileName;

    private String needMaterial;
    private String materials;
    private String receiver;
    private String receiverPhoneNumber;
    private String receiverArea;
    private String receiverAddress;

    //默认 （0）
    private String status;

    private String createTime;
    private String updateTime;


    private int passLoan0Count;
    private int totalLoan0Count;
    private int passLoan1Count;
    private int totalLoan1Count;

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
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

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPosterType() {
        return posterType;
    }

    public void setPosterType(String posterType) {
        this.posterType = posterType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPosterFileName() {
        return posterFileName;
    }

    public void setPosterFileName(String posterFileName) {
        this.posterFileName = posterFileName;
    }

    public String getNeedMaterial() {
        return needMaterial;
    }

    public void setNeedMaterial(String needMaterial) {
        this.needMaterial = needMaterial;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhoneNumber() {
        return receiverPhoneNumber;
    }

    public void setReceiverPhoneNumber(String receiverPhoneNumber) {
        this.receiverPhoneNumber = receiverPhoneNumber;
    }

    public String getReceiverArea() {
        return receiverArea;
    }

    public void setReceiverArea(String receiverArea) {
        this.receiverArea = receiverArea;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public int getPassLoan0Count() {
        return passLoan0Count;
    }

    public void setPassLoan0Count(int passLoan0Count) {
        this.passLoan0Count = passLoan0Count;
    }

    public int getTotalLoan0Count() {
        return totalLoan0Count;
    }

    public void setTotalLoan0Count(int totalLoan0Count) {
        this.totalLoan0Count = totalLoan0Count;
    }

    public int getPassLoan1Count() {
        return passLoan1Count;
    }

    public void setPassLoan1Count(int passLoan1Count) {
        this.passLoan1Count = passLoan1Count;
    }

    public int getTotalLoan1Count() {
        return totalLoan1Count;
    }

    public void setTotalLoan1Count(int totalLoan1Count) {
        this.totalLoan1Count = totalLoan1Count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}