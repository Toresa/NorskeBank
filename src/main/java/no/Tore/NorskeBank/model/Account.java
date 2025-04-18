package no.Tore.NorskeBank.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    private Long accountId;
    private String accountName;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    @Transient
    private boolean dirty = false; // Transient field to track changes

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
        markDirty(); // Mark as dirty when the ID changes
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
        markDirty(); // Mark as dirty when the name changes
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        markDirty(); // Mark as dirty when the balance changes
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        markDirty(); // Mark as dirty when the customer changes
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
        markDirty(); // Mark as dirty when the transactions list changes
    }

    // Method to add a transaction and update the balance
    public void addTransaction(String type, double amount, LocalDate date) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }

        // Create a new transaction
        Transaction transaction = new Transaction(null, this, type, amount, date);
        transactions.add(transaction);

        // Update the account balance based on the transaction type
        if ("deposit".equalsIgnoreCase(type)) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Deposit amount must be positive");
            }
            this.balance += amount;
        } else if ("withdrawal".equalsIgnoreCase(type)) {
            if (amount > this.balance) {
                throw new IllegalArgumentException("Insufficient funds for withdrawal");
            }
            this.balance -= amount;
        } else {
            throw new IllegalArgumentException("Invalid transaction type: " + type);
        }

        markDirty(); // Mark as dirty after adding a transaction
    }

    // Mark the entity as dirty
    private void markDirty() {
        this.dirty = true;
    }

    // Check if the entity is dirty
    public boolean isDirty() {
        return dirty;
    }
}
