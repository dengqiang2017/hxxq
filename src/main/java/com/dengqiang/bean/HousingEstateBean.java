package com.dengqiang.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 小区信息
 * @author 邓强
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class HousingEstateBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private UserInfoBean operator;//操作者/合作者
	private String housingName;//小区名称
	private String com_id;//小区编码
	private String address;//地址
	private int household;//户数,用于投票时候判断有效票数是否达到指定比例
	private BigDecimal amountPaid;//支付金额
	private Date availableDate;//可使用截止日期
	private int auditStatus=0;//审核状态,0-未审核,1-已审核,2-已失效
	private UserInfoBean auditor;//审核人
	private Date auditDate;//审核时间
	public HousingEstateBean() {}
	public HousingEstateBean(int id) {
		this.id=id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getHousingName() {
		return housingName;
	}
	public void setHousingName(String housingName) {
		this.housingName = housingName;
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
	public String getCom_id() {
		return com_id;
	}
	public void setCom_id(String com_id) {
		this.com_id = com_id;
	}
	
}
