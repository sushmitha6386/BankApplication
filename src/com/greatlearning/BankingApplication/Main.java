package com.greatlearning.BankingApplication;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		boolean validUser = false;
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("-----------!!!!!!----WELCOME TO BANKING APPLICATION----!!!!!!------------");
			System.out.println("Enter your Account Number : ");
			String accountNumber = scan.nextLine();
			
			System.out.println("Enter your Password : ");
			String password = scan.nextLine();
			
			UserVerification userVerify = new UserVerification();
			boolean user = userVerify.VerifyUser(accountNumber, password);
			
			if(user){
				validUser =true;
				System.out.println("!!! Login Successfull !!!");
				BankOperations bankOperation = new BankOperations();
				bankOperation.InitBankOperation(accountNumber);
			}
			else
			{
				System.out.println("!!! Login Attempt unsuccessfull. -- Invalid Password -- Please try again !!!");
			}
		}while(!validUser);
		scan.close();
	}
}
