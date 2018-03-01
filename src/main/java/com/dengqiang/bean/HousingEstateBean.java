package com.dengqiang.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 小区信息
 * @author 邓强
 *
 */
public class HousingEstateBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;//小区名称
	private String address;//地址
	private int household;//家庭数
	private BigDecimal amountPaid;//支付金额
	private Date availableDate;//可使用截止日期
	private UserInfoBean operator;//操作者/合作者
	private int auditStatus=0;//审核状态,0-未审核,1-已审核,2-已失效
	private UserInfoBean auditor;//审核人
	private Date auditDate;//审核时间
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getHousehold() {
		return household;
	}
	public void setHousehold(int household) {
		this.household = household;
	}
	public BigDecimal getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}
	public Date getAvailableDate() {
		return availableDate;
	}
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}
	public UserInfoBean getOperator() {
		return operator;
	}
	public void setOperator(UserInfoBean operator) {
		this.operator = operator;
	}
	public int getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(int auditStatus) {
		this.auditStatus = auditStatus;
	}
	public UserInfoBean getAuditor() {
		return auditor;
	}
	public void setAuditor(UserInfoBean auditor) {
		this.auditor = auditor;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}
