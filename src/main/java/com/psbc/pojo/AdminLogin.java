package com.psbc.pojo;


public class AdminLogin {
    private Long id;

    private String usercode;

    private String logintime;

    private String loginstatus;

    private String loginnum;

    private String sessiontime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public String getLoginstatus() {
		return loginstatus;
	}

	public void setLoginstatus(String loginstatus) {
		this.loginstatus = loginstatus;
	}

	public String getLoginnum() {
		return loginnum;
	}

	public void setLoginnum(String loginnum) {
		this.loginnum = loginnum;
	}

	public String getSessiontime() {
		return sessiontime;
	}

	public void setSessiontime(String sessiontime) {
		this.sessiontime = sessiontime;
	}

    
}