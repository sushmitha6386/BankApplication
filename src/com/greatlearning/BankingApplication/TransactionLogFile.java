package com.greatlearning.BankingApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionLogFile {

	public void transactionFile(String message,String accountNumber,int operationAmt,int balance,String operationName) throws IOException {
		File myFile = new File("transaction.txt");
		if(myFile.createNewFile())
		{
			System.out.println("Transaction file created ");
		}
		FileWriter writer = new FileWriter("transaction.txt", true);
		String fullMsg = message + "Customer Info - Account Number: " + accountNumber + " " + operationName + 
				" Amt : " + operationAmt + " Total Balance : " + balance + "\r\n";
		writer.write(fullMsg);
		writer.close();
	}
	
	public void readLogs(String accountNumber) {
	File myfile = new File("transaction.txt");
	Path path = Paths.get("transaction.txt");
	
	if(myfile.exists())
	{
		List<String> stringList = getLinesContain(path, accountNumber);
		for(String item:stringList) {
    		System.out.println(item + "\r\n");
		}
	}
	else {
	      System.out.println("The file does not exist.");
	    }
	}
	
	private List<String> getLinesContain(Path path, String accNum) {
		List<String> filteredList = null;
		
		try(Stream<String> stream = Files.lines(path)){
			filteredList = stream.filter(line -> line.contains(accNum))
                    .collect(Collectors.toList());
		}catch (IOException ioe) {
            // exception handling here
        }
        return filteredList;	
	}
	
}

