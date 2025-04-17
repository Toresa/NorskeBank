package no.Tore.NorskeBank.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id    
    private Long customerId;
    private String customerName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    // Calculate total balance
    @Transient
    public double getTotalBalance() {
        return accounts != null ? accounts.stream().mapToDouble(Account::getBalance).sum() : 0.0;
    }
}
