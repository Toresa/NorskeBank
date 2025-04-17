package no.Tore.NorskeBank.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false) // Foreign key column
    private Account account;

    private String type; // e.g., "deposit", "withdrawal"
    private double amount;
    private LocalDate date; // e.g., "2023-10-01"

    public Transaction() {
    }

    public Transaction(Long id, Account account, String type, double amount, LocalDate date) {
        this.id = id;
        this.account = account;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
