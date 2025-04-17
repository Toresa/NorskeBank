package no.Tore.NorskeBank;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import no.Tore.NorskeBank.model.Customer;
import no.Tore.NorskeBank.repository.CustomerRepository;
import no.Tore.NorskeBank.model.Account;
import no.Tore.NorskeBank.repository.AccountRepository;

@SpringBootTest(classes = NorskeBankApplication.class)
class NorskeBankApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Test
	public void testCustomerRepository() {
		// This test will check if the CustomerRepository bean is loaded correctly
		// You can add more specific tests here as needed		 
		List<Customer> customers = customerRepository.findAll();		
		assertTrue(customers.size() > 0, "Customer list should not be empty");
	}

	@Test
	public void testCustomerRepositoryFindByCustomerId() {
		// This test will check if the findByCustomerId method works correctly
		// You can add more specific tests here as needed
		Long customerId = 1L; // Replace with an actual customer ID from your database
		Customer customer = customerRepository.findByCustomerId(customerId);
		assertTrue(customer != null, "Customer should not be null");
		assertTrue(customer.getCustomerId().equals(customerId), "Customer ID should match");
	}

	@Test
	public void testAccountRepository() {
		// This test will check if the AccountRepository bean is loaded correctly
		// You can add more specific tests here as needed
		List<Account> accounts = accountRepository.findAll();
		assertTrue(accounts.size() > 0, "Account list should not be empty");
	}

	@Test
	@Transactional
	public void testCustomerTotalBalance() {
		Long customerId = 1L; // Replace with an actual customer ID from your database
		Customer customer = customerRepository.findByCustomerId(customerId);
		assertTrue(customer != null, "Customer should not be null");

		double totalBalance = customer.getTotalBalance();
		System.out.println("Total Balance for Customer ID " + customerId + ": " + totalBalance);
		assertTrue(totalBalance > 0, "Total balance should be greater than 0");
	}

}
