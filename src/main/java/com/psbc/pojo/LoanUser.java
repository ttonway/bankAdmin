package com.psbc.pojo;

public class LoanUser {
    private Long loanid;

	/**
	 * ҵ������
	 * 1. �����
	 * 2. ���Ŵ�
	 */
	private String loanType;

    private String usernm;

    private String phonenum;

    private String area;

    private String bank;

    private String referrals;

    private Integer id;

	/**
	 * ���Ŵ�: ������
	 */
	private Integer loanNum;
	/**
	 * ���Ŵ�: ��������
	 * �����: ������
	 */
    private String loanNum1;
	/**
	 * �����: ��ҵ��Ϣ��һ����
	 */
    private String workunit;
	/**
	 * �����: ��ҵ��Ϣ��������
	 */
	private String workunit2;
	/**
	 * �����: �Ƿ�Ϊ������
	 */
	private String localPerson;
	/**
	 * �����:�����Ƿ��з���
	 */
	private String house;
	/**
	 * �����: ������ʽ
	 */
	private String guaranteeType;

    private String createtime;
    //����ϵ ��0�� ����� ��1�� ����� ��2�� �Ѳ��� ��3�� ������ ��4��
    private String status;
    
    private String remark;
    
    private String updatetime;
    
    private String utmsrc;
    
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

	public String getLoanNum1() {
		return loanNum1;
	}

	public void setLoanNum1(String loanNum1) {
		this.loanNum1 = loanNum1;
	}
}