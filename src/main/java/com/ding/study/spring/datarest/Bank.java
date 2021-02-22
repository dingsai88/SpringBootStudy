package com.ding.study.spring.datarest;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description  
 * @Author  linmengmeng
 * @Date 2021-02-22 10:05:50 
 */

@Entity
@Table ( name ="bank_copy" , schema = "")
public class Bank implements Serializable {

	private static final long serialVersionUID =  5333151291435574742L;

	/**
	 * 自增主键
	 */
	@Id
   	@Column(name = "id" )
	private Long id;

	/**
	 * 银行代码
	 */
   	@Column(name = "bank_code" )
	private String bankCode;

	/**
	 * 银行名称
	 */
	@JsonIgnore
   	@Column(name = "bank_name" )
	private String bankName;

	/**
	 * 缩写
	 */
   	@Column(name = "bank_name_abbr" )
	private String bankNameAbbr;

	/**
	 * 0：有效 1无效
	 */
   	@Column(name = "status" )
	private Integer status;

	/**
	 * 是否支持多张卡0:不支持 1：支持,用于展示
	 */
   	@Column(name = "multi_cards" )
	private Integer multiCards;

	/**
	 * 客服电话
	 */
   	@Column(name = "service_phone" )
	private String servicePhone;

   	@Column(name = "introduce" )
	private String introduce;

	/**
	 * 开户协议
	 */
   	@Column(name = "open_agreement" )
	private String openAgreement;

	/**
	 * 充值协议
	 */
   	@Column(name = "recharge_agreement" )
	private String rechargeAgreement;

   	@Column(name = "create_time" )
	private Date createTime;

   	@Column(name = "update_time" )
	private Date updateTime;

	/**
	 * 是否支持人脸识别：0不支持 1支持
	 */
   	@Column(name = "face_rec" )
	private Integer faceRec;

	/**
	 * 是否可以从相册上传照片：0不可以 1可以
	 */
   	@Column(name = "photo_album" )
	private Integer photoAlbum;

	/**
	 * 是否开启老客模式，默认关闭
	 */
   	@Column(name = "regular_customers" )
	private Integer regularCustomers;

	/**
	 * 更多信息开户：两个值，需要1、不需要0
	 */
   	@Column(name = "open_more_info" )
	private Integer openMoreInfo;

	/**
	 * 换绑卡人脸识别换手机号人脸识别：两个值，需要1、不需要0
	 */
   	@Column(name = "change_card_face" )
	private Integer changeCardFace;

	/**
	 * 是否支持线下充值配置：两个值，支持1、不支持0
	 */
   	@Column(name = "offline_recharge" )
	private Integer offlineRecharge;

	/**
	 * SourceTypeEnum:1产品中心；2君正
	 */
   	@Column(name = "source" )
	private Integer source;

	/**
	 * 支持销户:两个值，支持1、不支持0
	 */
   	@Column(name = "account_cancel" )
	private Integer accountCancel;

	/**
	 * 协议支付:普通支付0，协议支付1，二次验证码协议支付2
	 */
   	@Column(name = "agreement_payment" )
	private Long agreementPayment;

	/**
	 * 支行名称
	 */
   	@Column(name = "bank_branch" )
	private String bankBranch;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNameAbbr() {
		return this.bankNameAbbr;
	}

	public void setBankNameAbbr(String bankNameAbbr) {
		this.bankNameAbbr = bankNameAbbr;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMultiCards() {
		return this.multiCards;
	}

	public void setMultiCards(Integer multiCards) {
		this.multiCards = multiCards;
	}

	public String getServicePhone() {
		return this.servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getIntroduce() {
		return this.introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getOpenAgreement() {
		return this.openAgreement;
	}

	public void setOpenAgreement(String openAgreement) {
		this.openAgreement = openAgreement;
	}

	public String getRechargeAgreement() {
		return this.rechargeAgreement;
	}

	public void setRechargeAgreement(String rechargeAgreement) {
		this.rechargeAgreement = rechargeAgreement;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getFaceRec() {
		return this.faceRec;
	}

	public void setFaceRec(Integer faceRec) {
		this.faceRec = faceRec;
	}

	public Integer getPhotoAlbum() {
		return this.photoAlbum;
	}

	public void setPhotoAlbum(Integer photoAlbum) {
		this.photoAlbum = photoAlbum;
	}

	public Integer getRegularCustomers() {
		return this.regularCustomers;
	}

	public void setRegularCustomers(Integer regularCustomers) {
		this.regularCustomers = regularCustomers;
	}

	public Integer getOpenMoreInfo() {
		return this.openMoreInfo;
	}

	public void setOpenMoreInfo(Integer openMoreInfo) {
		this.openMoreInfo = openMoreInfo;
	}

	public Integer getChangeCardFace() {
		return this.changeCardFace;
	}

	public void setChangeCardFace(Integer changeCardFace) {
		this.changeCardFace = changeCardFace;
	}

	public Integer getOfflineRecharge() {
		return this.offlineRecharge;
	}

	public void setOfflineRecharge(Integer offlineRecharge) {
		this.offlineRecharge = offlineRecharge;
	}

	public Integer getSource() {
		return this.source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getAccountCancel() {
		return this.accountCancel;
	}

	public void setAccountCancel(Integer accountCancel) {
		this.accountCancel = accountCancel;
	}

	public Long getAgreementPayment() {
		return this.agreementPayment;
	}

	public void setAgreementPayment(Long agreementPayment) {
		this.agreementPayment = agreementPayment;
	}

	public String getBankBranch() {
		return this.bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
	}

	@Override
	public String toString() {
		return "{" +
					"id='" + id + '\'' +
					"bankCode='" + bankCode + '\'' +
					"bankName='" + bankName + '\'' +
					"bankNameAbbr='" + bankNameAbbr + '\'' +
					"status='" + status + '\'' +
					"multiCards='" + multiCards + '\'' +
					"servicePhone='" + servicePhone + '\'' +
					"introduce='" + introduce + '\'' +
					"openAgreement='" + openAgreement + '\'' +
					"rechargeAgreement='" + rechargeAgreement + '\'' +
					"createTime='" + createTime + '\'' +
					"updateTime='" + updateTime + '\'' +
					"faceRec='" + faceRec + '\'' +
					"photoAlbum='" + photoAlbum + '\'' +
					"regularCustomers='" + regularCustomers + '\'' +
					"openMoreInfo='" + openMoreInfo + '\'' +
					"changeCardFace='" + changeCardFace + '\'' +
					"offlineRecharge='" + offlineRecharge + '\'' +
					"source='" + source + '\'' +
					"accountCancel='" + accountCancel + '\'' +
					"agreementPayment='" + agreementPayment + '\'' +
					"bankBranch='" + bankBranch + '\'' +
				'}';
	}

}
