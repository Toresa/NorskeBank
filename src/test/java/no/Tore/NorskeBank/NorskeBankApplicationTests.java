package no.Tore.NorskeBank;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import no.Tore.NorskeBank.model.Customer;
import no.Tore.NorskeBank.model.Transaction;
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

	@Test
	@Transactional
	public void testAccountWithTransactions() {
		Long accountId = 1L; // Replace with an actual account ID from your database
		Account account = accountRepository.findAccountWithTransactions(accountId);
		assertTrue(account != null, "Account should not be null");
		assertTrue(account.getTransactions().size() > 0, "Account should have transactions");
		System.out.println("Account ID: " + account.getAccountId());
		System.out.println("Transactions: " + account.getTransactions());
	}

	@Test
	@Transactional
	public void testAccountBalanceAfterTransaction() {
		Long accountId = 1L; // Replace with an actual account ID from your database
		Account account = accountRepository.findById(accountId).orElse(null);
		assertTrue(account != null, "Account should not be null");
		double beforeBalance = account.getBalance();
		account.addTransaction("deposit", 123.45, LocalDate.now()); // Add a deposit transaction
		accountRepository.save(account); // Save the account after adding the transaction
		double afterBalance = account.getBalance();
		assertTrue(afterBalance == beforeBalance + 123.45, "Balance should be updated correctly after deposit");
	}

}
