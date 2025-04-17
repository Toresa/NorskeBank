package no.Tore.NorskeBank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import no.Tore.NorskeBank.service.AccountService;
import no.Tore.NorskeBank.model.Account;

@Controller
public class AccountController {
    // This class is a placeholder for the AccountController implementation.
    // It should contain methods to handle HTTP requests related to accounts.
    // For example, methods to create, read, update, and delete accounts.
    // The actual implementation will depend on the specific requirements of the application.
    @Autowired AccountService accountService;
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Example method to get all accounts (to be implemented)
    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccountById(Long id) {
        return accountService.getAccountById(id).orElse(null);
    }

    @GetMapping("/accounts/customer/{customerId}")
    public List<Account> getAccountsByCustomerId(Long customerId) {
        return accountService.getAccountsByCustomerId(customerId);
    }

}