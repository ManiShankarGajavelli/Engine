package com.Engine.Model;

import java.util.Date;

public class StatementVO {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StatementVO [TransactionDate=" + TransactionDate + ", ValueDate=" + ValueDate + ",\n Description="
				+ Description + ", ReferenceNo=" + ReferenceNo + ",\n Debit=" + Debit + ", Credit=" + Credit
				+ ", Balance=" + Balance + "]";
	}
	private Date  TransactionDate;
	private Date  ValueDate;
	private String  Description;
	private String  ReferenceNo;
	private String  Debit;
	private String  Credit;
	private String  Balance;
	public String getReferenceNo() {
		return ReferenceNo;
	}
	public void setReferenceNo(String referenceNo) {
		ReferenceNo = referenceNo;
	}
	public Date getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(Date date) {
		TransactionDate = date;
	}
	public Date getValueDate() {
		return ValueDate;
	}
	public void setValueDate(Date date) {
		ValueDate = date;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getDebit() {
		return Debit;
	}
	public void setDebit(String debit) {
		Debit = debit;
	}
	public String getCredit() {
		return Credit;
	}
	public void setCredit(String credit) {
		Credit = credit;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
}
