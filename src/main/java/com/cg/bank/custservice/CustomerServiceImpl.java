package com.cg.bank.custservice;

import com.cg.bank.custdao.CustomerDAO;
import com.cg.bank.custdao.CustomerDAOImpl;
import com.cg.bank.customer.Customer;
import com.cg.bank.transaction.Transaction;

public class CustomerServiceImpl implements CustomerService {

	CustomerDAO custdao = new CustomerDAOImpl();
	Customer customer = new Customer();
	boolean validateUserInput(Customer customer) {
		boolean b=false;
		if (customer.getMobileNo().length() == 10) {
			if (customer.getAadhaarNo().length() == 12) {
				b=true;
			} else
				b=false;
		} else
			b=false;
		
		return b;
		
	}

	public Long register(Customer customer) {
		boolean b= validateUserInput(customer);
		if(b) {
			long accNo = custdao.register(customer);
			return accNo;
		}
		else
			return (long) 0;

	}

	public Customer login(long acc, String pswd) {
		return custdao.login(acc, pswd); 
	}
	public long deposit(Customer customer,long amt) {
		return custdao.deposit(customer,amt);
	}

	public long withdraw(Customer customer,long amt) {
		return custdao.withdraw(customer,amt);
	}

	public long displayBalance(Customer customer,long amt) {
		return custdao.showBalance(customer,amt);
		
	}

	public Transaction fundTransfer(Transaction transaction) {
		return custdao.fundTransfer(transaction);
	}

}
