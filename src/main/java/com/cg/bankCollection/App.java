package com.cg.bankCollection;

import java.util.Scanner;

import com.cg.bank.customer.Customer;
import com.cg.bank.custservice.CustomerService;
import com.cg.bank.custservice.CustomerServiceImpl;
import com.cg.bank.transaction.Transaction;
import com.cg.bank.uiexceptions.InvalidIDException;
import com.cg.bank.uiexceptions.InvalidInputException;
import com.cg.bank.uiexceptions.TransactionException;
import com.cg.bank.uiexceptions.WithDrawlException;

public class App {
	static Transaction transaction = new Transaction();
	static CustomerService custservice = new CustomerServiceImpl();
	
	public static Scanner sc = new Scanner(System.in);

	public void disp(Customer customer) {
	long amt = 0, res = 0;
		System.out.println("enter 1. deposit 2. withdraw 3. show balance 4.Fund Transfer");
		int i = sc.nextInt();
		switch (i) {
		case 1:
			System.out.println("enter amount to deposit");
			amt = sc.nextLong();
			res = custservice.deposit(customer,amt);
			System.out.println("Balance is" + res);
			break;
		case 2:
			System.out.println("enter amount to debit");
			amt = sc.nextLong();
			res = custservice.withdraw(customer,amt);
			if (res != 0)
				System.out.println("Balance is" + res);
			else
				try {
					throw new WithDrawlException();
				} catch (WithDrawlException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
		case 3:
			res = custservice.displayBalance(customer,amt);
			System.out.println("Balance is" + res);
			break;

		case 4:
			transaction.setFromAccountNo(customer.getAccountNo());
			System.out.println("enter destination account no");
			transaction.setToAccountNo(sc.nextLong());
			System.out.println("enter amount to be transferred");
			transaction.setAmountTransferred(sc.nextLong());
			transaction = custservice.fundTransfer(transaction);
			if (transaction != null) {
				System.out.println(transaction.getAmountTransferred() + " is transferred from "
						+ transaction.getFromAccountNo() + " to " + transaction.getToAccountNo());
			System.out.println("Transaction Id is"+transaction.getTransactionId());
			}
			else
				try {
					throw new TransactionException();
				} catch (TransactionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	public static void main(String[] args) {
		App app = new App();
		long accNo=0;
		Customer customer = new Customer();
		Customer customer1 = new Customer();
		Scanner sc = new Scanner(System.in);
		do {
		System.out.println("enter 1. Register 2. Login 3.Exit");
		int c = sc.nextInt();
		switch (c) {
		case 1:
			System.out.println("enter first_name");
			customer.setFirstName(sc.next());
			System.out.println("enter last_name");
			customer.setLastName(sc.next());
			System.out.println("enter email");
			customer.setEmailId(sc.next());
			System.out.println("enter password");
			customer.setPassword(sc.next());
			System.out.println("enter pan card ");
			customer.setPancardNo(sc.next());
			System.out.println("enter aadhaar no");
			customer.setAadhaarNo(sc.next());
			System.out.println("enter address");
			customer.setAddress(sc.next());
			System.out.println("enter mobile number");
			customer.setMobileNo(sc.next());
			customer.setBalance(0);

			 accNo = custservice.register(customer);
			if (accNo==0) {
				try {
					throw new InvalidInputException();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				System.out.println("welcome "+customer.getFirstName());
				System.out.println("Account No is " +accNo);
			}
			break;
		case 2:
			System.out.println("enter account no");
			long acc = sc.nextLong();
			System.out.println("enter password");
			String pswd = sc.next();
			customer1 = custservice.login(acc, pswd);
			if (customer1 != null) {
				System.out.println("welcome " + customer1.getFirstName());
				app.disp(customer1);
			} else
				try {
					throw new InvalidIDException();
				} catch (InvalidIDException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
		case 3:System.exit(0);

		}
		}while(true);
	}

}
