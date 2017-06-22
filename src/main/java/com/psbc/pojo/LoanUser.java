package com.psbc.pojo;

public class LoanUser {
	private Long loanid;

	/**
	 * 业务类型
	 * 1. 商易贷
	 * 2. 邮信贷
	 */
	private String loanType;

	private String usernm;

	private String phonenum;

	private String area;

	private String bank;

	private String referrals;

	private Integer id;

	/**
	 * 申请金额
	 */
	private Integer loanNum;

	/**
	 * 商易贷: 行业信息（一级）
	 */
	private String workunit;
	/**
	 * 商易贷: 行业信息（二级）
	 */
	private String workunit2;
	/**
	 * 商易贷: 是否为本地人
	 */
	private String localPerson;
	/**
	 * 商易贷:本地是否有房产
	 */
	private String house;
	/**
	 * 商易贷: 担保方式
	 */
	private String guaranteeType;

	private String createtime;
	//待联系 （0） 待审核 （1） 已审核 （2） 已驳回 （3） 待调整 （4）
	private String status;

	private String remark;

	private String updatetime;

	private String utmsrc;

	private String fromUserCode;

	private String fromUserName;
	private String fromUserPhone;

	public Long getLoanid() {
		return loanid;
	}

	public void setLoanid(Long loanid) {
		this.loanid = loanid;
	}

	public String getUsernm() {
		return usernm;
	}

	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getReferrals() {
		return referrals;
	}

	public void setReferrals(String referrals) {
		this.referrals = referrals;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLoanNum() {
		return loanNum;
	}

	public void setLoanNum(Integer loanNum) {
		this.loanNum = loanNum;
	}

	public String getWorkunit() {
		return workunit;
	}

	public void setWorkunit(String workunit) {
		this.workunit = workunit;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUtmsrc() {
		return utmsrc;
	}

	public void setUtmsrc(String utmsrc) {
		this.utmsrc = utmsrc;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getWorkunit2() {
		return workunit2;
	}

	public void setWorkunit2(String workunit2) {
		this.workunit2 = workunit2;
	}

	public String getLocalPerson() {
		return localPerson;
	}

	public void setLocalPerson(String localPerson) {
		this.localPerson = localPerson;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getFromUserCode() {
		return fromUserCode;
	}

	public void setFromUserCode(String fromUserCode) {
		this.fromUserCode = fromUserCode;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getFromUserPhone() {
		return fromUserPhone;
	}

	public void setFromUserPhone(String fromUserPhone) {
		this.fromUserPhone = fromUserPhone;
	}
}