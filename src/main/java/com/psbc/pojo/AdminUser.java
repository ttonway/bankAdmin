package com.psbc.pojo;

public class AdminUser {
    private Long userId;

    private String userName;

    private String userCode;

    private String password;

    private String bank;

    private String role;
    
    private String loginTime;

    @Override
    public String toString() {
        return "AdminUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userCode='" + userCode + '\'' +
                ", password='" + password + '\'' +
                ", bank='" + bank + '\'' +
                ", role='" + role + '\'' +
                ", loginTime='" + loginTime + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}