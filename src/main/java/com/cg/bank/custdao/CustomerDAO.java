package com.cg.bank.custdao;

import com.cg.bank.customer.Customer;
import com.cg.bank.transaction.Transaction;

public interface CustomerDAO {
	Long register(Customer customer);
	Customer login(long acc,String pswd);
	Transaction fundTransfer(Transaction transaction);
	long deposit(Customer customer,long amt);
	long withdraw(Customer customer,long amt);
	long showBalance(Customer customer,long amt);


}
