package com.koocloud.facerecognition.showdata.entity;

import javax.persistence.MappedSuperclass;

/**
 * 因CardInfo和MainData 中的很多属性一致,所以MainAttribute为CardInfo和MainData 表中共有属性,
 * 方便MainData数据的录入
 */
@MappedSuperclass
public class MainAttribute {
    private String appId;//appid

    private String certificateNo;//证件编号(18位十六进制数)

    private String userName;//姓名

    private Integer certificateType;//证件类别(1-9)

    private String cardNo;//票卡号(10位十进制数)

    private Integer cardType;//票卡类型(1-9)

    private Double cardBalance;//卡余额

    private Double depositMoney;//办卡押金

    private Integer recoveryFlag;//票卡回收标志(0,1)

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public Double getDepositMoney() {
        return depositMoney;
    }

    public void setDepositMoney(Double depositMoney) {
        this.depositMoney = depositMoney;
    }

    public Integer getRecoveryFlag() {
        return recoveryFlag;
    }

    public void setRecoveryFlag(Integer recoveryFlag) {
        this.recoveryFlag = recoveryFlag;
    }

    public MainAttribute(String appId, String certificateNo, String userName, Integer certificateType, String cardNo, Integer cardType, Double cardBalance, Double depositMoney, Integer recoveryFlag) {
        this.appId = appId;
        this.certificateNo = certificateNo;
        this.userName = userName;
        this.certificateType = certificateType;
        this.cardNo = cardNo;
        this.cardType = cardType;
        this.cardBalance = cardBalance;
        this.depositMoney = depositMoney;
        this.recoveryFlag = recoveryFlag;
    }

    public MainAttribute() {
    }
}
