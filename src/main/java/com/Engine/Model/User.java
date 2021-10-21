package com.Engine.Model;

import java.util.Date;

public class User {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User Details [Account userName=" + l_sName + ", Address of User=" + l_sAddress + ", \nUser's bank Account Number=" + l_sAccountNo
				+ ", BANK IFCSCode=" + l_sIFCSCode + ", Branch Name=" + l_sBnkBranchName + ", \nBank Address="
				+ l_sBnkAddress + ", \nAccount MICR=" + l_sMICR + ", Bank Interest Rate=" + l_sBnkInterestRate + "\nCustomerId is " +l_sCustomerId+""
						+ " CurrencyType "+l_sCurrencyType +"]";
	}
	private String l_sName;
	private String l_sAddress;
	private String l_sAccountNo;
	private String l_sIFCSCode;
	private String l_sBnkBranchName;
	private String l_sBnkAddress;
	private String l_sMICR;
	private String l_sBnkInterestRate;
	private String l_sCustomerId;
	private String l_sCurrencyType;
	private String l_sEmail;
	private Date FromAccDate;
	public Date getFromAccDate() {
		return FromAccDate;
	}
	public void setFromAccDate(Date fromAccDate) {
		FromAccDate = fromAccDate;
	}
	public Date getToAccDate() {
		return ToAccDate;
	}
	public void setToAccDate(Date toAccDate) {
		ToAccDate = toAccDate;
	}
	private Date ToAccDate;
	/**
	 * @return the l_sEmail
	 */
	public String getL_sEmail() {
		return l_sEmail;
	}
	/**
	 * @param l_sEmail the l_sEmail to set
	 */
	public void setL_sEmail(String l_sEmail) {
		this.l_sEmail = l_sEmail;
	}
	/**
	 * @return the l_sCurrencyType
	 */
	public String getL_sCurrencyType() {
		return l_sCurrencyType;
	}
	/**
	 * @param l_sCurrencyType the l_sCurrencyType to set
	 */
	public void setL_sCurrencyType(String l_sCurrencyType) {
		this.l_sCurrencyType = l_sCurrencyType;
	}
	/**
	 * @return the l_sCustomerId
	 */
	public String getL_sCustomerId() {
		return l_sCustomerId;
	}
	/**
	 * @param l_sCustomerId the l_sCustomerId to set
	 */
	public void setL_sCustomerId(String l_sCustomerId) {
		this.l_sCustomerId = l_sCustomerId;
	}
	public String getL_sName() {
		return l_sName;
	}
	public void setL_sName(String l_sName) {
		this.l_sName = l_sName;
	}
	public String getL_sAddress() {
		return l_sAddress;
	}
	public void setL_sAddress(String l_sAddress) {
		this.l_sAddress = l_sAddress;
	}
	public String getL_sAccountNo() {
		return l_sAccountNo;
	}
	public void setL_sAccountNo(String l_sAccountNo) {
		this.l_sAccountNo = l_sAccountNo;
	}
	public String getL_sIFCSCode() {
		return l_sIFCSCode;
	}
	public void setL_sIFCSCode(String l_sIFCSCode) {
		this.l_sIFCSCode = l_sIFCSCode;
	}
	public String getL_sBnkBranchName() {
		return l_sBnkBranchName;
	}
	public void setL_sBnkBranchName(String l_sBnkBranchName) {
		this.l_sBnkBranchName = l_sBnkBranchName;
	}
	public String getL_sBnkAddress() {
		return l_sBnkAddress;
	}
	public void setL_sBnkAddress(String l_sBnkAddress) {
		this.l_sBnkAddress = l_sBnkAddress;
	}
	public String getL_sMICR() {
		return l_sMICR;
	}
	public void setL_sMICR(String l_sMICR) {
		this.l_sMICR = l_sMICR;
	}
	public String getL_sBnkInterestRate() {
		return l_sBnkInterestRate;
	}
	public void setL_sBnkInterestRate(String l_sBnkInterestRate) {
		this.l_sBnkInterestRate = l_sBnkInterestRate;
	}
}
