package com.cg.bank.custdao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cg.bank.customer.Customer;
import com.cg.bank.transaction.Transaction;

class CustomerDAOImplTest {
	long bal=0;
	CustomerDAOImpl cust=new CustomerDAOImpl();
	Customer customer=new Customer();
	Transaction transaction=new Transaction();
	Customer customer1=new Customer();

	@Test
	void testRegister() {
		customer.setAadhaarNo("123151081111");
		customer.setAddress("Hyd");
		customer.setBalance(100);
		customer.setEmailId("shruthi@gmail");
		customer.setFirstName("Shruthi");
		customer.setLastName("Annamaneni");
		customer.setMobileNo("9978675456");
		customer.setPancardNo("987656");
		customer.setPassword("shruthi27");
		long acc= cust.register(customer);
		assertEquals(2000,acc);
		
	}

	@Test
	void testLogin() {
		customer1=cust.login(1019, "mad");
		assertEquals(customer1,customer1);
	}

	@Test
	void testDeposit() {
		 bal=cust.deposit(customer, 1000);
		 customer.setBalance(bal);
		assertEquals(1000,customer.getBalance());
	}

	@Test
	void testWithdraw() {
		customer.setBalance(10000);
		assertEquals(5000, cust.withdraw(customer,5000));
	}

	@Test
	void testShowBalance() {
		customer.setBalance(1000);
		bal=cust.showBalance(customer, bal);
		assertEquals(1000, bal);
	}

	@Test
	void testFundTransfer() {
		transaction.setAmountTransferred(10);
		transaction.setFromAccountNo(1004);
		transaction.setToAccountNo(1001);
		assertEquals(null, cust.fundTransfer(transaction));
	}

}
