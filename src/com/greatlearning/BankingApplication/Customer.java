package com.greatlearning.BankingApplication;

public class Customer {
	private String acccountNumber;
	private String password;
	private Integer accountBalance;
	
	public Customer(String acccountNumber, String password, Integer accountBalance) {
		super();
		this.acccountNumber = acccountNumber;
		this.password = password;
		this.accountBalance = accountBalance;
	}

	public String getAcccountNumber() {
		return acccountNumber;
	}

	public void setAcccountNumber(String acccountNumber) {
		this.acccountNumber = acccountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Integer accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Customer [acccountNumber=" + acccountNumber + ", password=" + password + ", accountBalance="
				+ accountBalance + "]";
	}

}
