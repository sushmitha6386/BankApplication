package com.greatlearning.BankingApplication;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;


public class BankOperations {

	Set<Customer> customerList = new HashSet<Customer>();
	Scanner scan = new Scanner(System.in);
	String operationName = null;
	TransactionLogFile txnLogFile = new TransactionLogFile();
	UserVerification userVerify = new UserVerification();
	
	
	public BankOperations(){
		customerList.add(new Customer("Sush123", "123", 10000));
		customerList.add(new Customer("Vish456", "456", 15000));
		customerList.add(new Customer("Kush789", "789", 20000));
	}
	
	public void InitBankOperation(String accountNumber){
		while (true){
			System.out.println("----Bank Operation----");
			System.out.println("Enter the operation you want to perform :");
			System.out.println("1. Deposit \n");
			System.out.println("2. Withdrawl \n");
			System.out.println("3. Transfer \n");
			System.out.println("4. Mini Statement \n");
			System.out.println("0. Logout \n");
			System.out.println("-----------------------");
			
			int choice = scan.nextInt();
			switch(choice){
			case 1: 
				System.out.println("Enter the amount you want to deposit :");
				int dAmount = scan.nextInt();
				amountDepositor(accountNumber, dAmount);
				System.out.println("Amount" +dAmount+"Rs Deposited");
				break;
				
			case 2:
				System.out.println("Enter the amount you want to withdraw :");
				int wAmount = scan.nextInt();
				amountWithdrawer(accountNumber, wAmount);
				break;
				
			case 3:
				Random randomNum = new Random();
				int actualOTP = randomNum.nextInt(5000);
				System.out.println("Enter the otp:  " + actualOTP);
				int userOTP = scan.nextInt();
				if(actualOTP == userOTP)
				{	
					System.out.println("OTP verified");
					System.out.println("Enter the amount you want to trnsfer: ");
					int tAmount = scan.nextInt();
					Scanner sc = new Scanner(System.in);
					System.out.println("Enter the Account Number of the reciever: ");
					String rAccountNumber = sc.nextLine();
					
					boolean verifyUser = userVerify.userVerificationBasedOnAccountNum(rAccountNumber);
					if(verifyUser) {
						moneyTransfer(accountNumber, rAccountNumber, tAmount);
					}else {
						System.out.println("Invalid account number ");
					}
					sc.close();
				}
				else {
					System.out.println("Invalid OTP");
				}
				break;
			case 4:
				txnLogFile.readLogs(accountNumber);
				break;
			case 0:
				System.out.println("!!!! -- Logging Off -- !!!!");
				System.exit(0);
				break;
			default:
				System.out.println("This is not a valid Menu Option! Please Select Another");
				break;
			}
		}
	}
	
	public void amountDepositor(String accountNumber, int depositAmount){
		customerList.stream().filter(c -> c.getAcccountNumber().equals(accountNumber)).collect(Collectors.toList())
		.forEach(data -> {
			try {
				int currentBalance = data.getAccountBalance();
				int updatedBalance = currentBalance + depositAmount;
				/* i have chosen set data structure so it will not accept duplicate */
				data.setAccountBalance(updatedBalance);
				customerList.add(data);
				displayUpdatedBalance(accountNumber, currentBalance, updatedBalance);
				String message = "My Deposit Transaction! ";
				operationName = "Deposit";
				txnLogFile.transactionFile(message,accountNumber,depositAmount,updatedBalance,operationName);

			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	public void amountWithdrawer(String accountNumber, int withdrawlAmt) {
		customerList.stream().filter(c -> c.getAcccountNumber().equals(accountNumber)).collect(Collectors.toList())
		.forEach(data -> {
			try {
				int currentBalance = data.getAccountBalance();
				/* if sufficient balance is available then allow withdrawal else send error message */
				if (currentBalance >= withdrawlAmt) {
				int updatedBalance = currentBalance - withdrawlAmt;
				data.setAccountBalance(updatedBalance);
				customerList.add(data);
				System.out.println("Amount " + withdrawlAmt + " withdrawl successfully");
				displayUpdatedBalance(accountNumber, currentBalance, updatedBalance);
				operationName = "Withdrawl";
				String message = "My Withdrawl Transaction! ";
				txnLogFile.transactionFile(message,accountNumber,withdrawlAmt,updatedBalance,operationName);
				} 
				else{
					System.out.println("Sorry! Insufficient Funds");
					System.out.println();
				}
			} catch (SecurityException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			}

		});
	}
	
	private void displayUpdatedBalance(String accNo, int currentBalance, int updatedBalance) {
		System.out.println("For Account number - " + " " + accNo + " Previous Balance : " + " " + currentBalance
				+ " updatedBalance : " + " " + updatedBalance);
	}
	
	public void moneyTransfer(String senderAccNum, String receipientAccNo, int transferAmt) {

		senderAccountInfo(senderAccNum, transferAmt);
		receiverAccountInfo(receipientAccNo, transferAmt);
	}

	private void senderAccountInfo(String senderAccNum, int transferAmt) {
		customerList.stream().filter(c -> c.getAcccountNumber().equals(senderAccNum)).collect(Collectors.toList())
		.forEach(data -> {
			try {
				int currentBalance = data.getAccountBalance();
				if(currentBalance >= transferAmt ) {
					int updatedBalance = currentBalance - transferAmt;
					data.setAccountBalance(updatedBalance);
					customerList.add(data);
					System.out.println("Sender Account Detail - " + " " + senderAccNum + "  Previous Balance : "
							+ " " + currentBalance + "  updatedBalance : " + " " + updatedBalance);
					operationName = "Transfer";
					String message = "My Transfer Transaction! ";
					txnLogFile.transactionFile(message,senderAccNum,transferAmt,updatedBalance,operationName);
				}
				else{
					System.out.println("Sorry! Insufficient Funds");
				}
			} 
			catch (SecurityException | IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void receiverAccountInfo(String receipientAccNo, int transferAmt) {
		customerList.stream().filter(c -> c.getAcccountNumber().equals(receipientAccNo)).collect(Collectors.toList())
		.forEach(data -> {
			try {
				int currentBalance = data.getAccountBalance();
				int updatedBalance = currentBalance + transferAmt;
				data.setAccountBalance(updatedBalance);
				customerList.add(data);
				System.out.println("Receiver Account Detail - " + " " + receipientAccNo + "  Previous Balance : "
									+ " " + currentBalance + "  updatedBalance : " + " " + updatedBalance);
			} 
			catch (SecurityException e) {
					e.printStackTrace();
			}

		});
	}
	
}
