package com.greatlearning.BankingApplication;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class UserVerification {

	Set<Customer> customerList = new HashSet<Customer>();
	boolean userVerified = false;

	public UserVerification() {
		customerList.add(new Customer("Sush123", "123", 10000));
		customerList.add(new Customer("Vish456", "456", 15000));
		customerList.add(new Customer("Kush789", "789", 20000));
	}
	
	public boolean VerifyUser(String accountNumber, String password){
		Set<Customer>customer = customerList.stream().filter(c -> c.getAcccountNumber().equals(accountNumber)).collect(Collectors.toSet());
		
		if(customer.size()>0){
			customer.forEach(data -> {
			if (data.getAcccountNumber().equals(accountNumber) && data.getPassword().equals(password)){
				userVerified = true;
			}
			else {
				userVerified = false;
			}
			});
		}
		else{
			userVerified = false;
		}
		return userVerified;
	}
	
	public Boolean userVerificationBasedOnAccountNum(String receivingAccounNum) {

		Set<Customer> customer = customerList.stream().filter(c -> c.getAcccountNumber().equals(receivingAccounNum)).collect(Collectors.toSet());
		if(customer.size() > 0) {
			customer.forEach(data -> {
				if (data.getAcccountNumber().equals(receivingAccounNum)) {
					userVerified = true;
				} else {
					userVerified = false;
				}
			});
			
		}else {
			userVerified = false;
		}
		return userVerified;
	}

}

