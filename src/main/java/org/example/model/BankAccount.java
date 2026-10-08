package org.example.model;

public class BankAccount implements  Cloneable {
    private Long accountId;

    private double balance ;

    private String currency;

    private AccountType type;


    private AccountStatus status ;

    private Customer customer;
    public Long getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public AccountType getType() {
        return type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public BankAccount() {
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", customer=" + customer +
                '}';
    }


    public static class AccountBuilder {
        private Long accountId;
        private double balance;
        private String currency;
        private AccountType type;
        private AccountStatus status;
        private Customer customer;

        public AccountBuilder accountId(Long id) {
            this.accountId = id;
            return this;
        }

        public AccountBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public AccountBuilder balance(double balance) {
            this.balance = balance;
            return this;
        }

        public AccountBuilder type(AccountType type) {
            this.type = type;
            return this;
        }

        public AccountBuilder status(AccountStatus status) {
            this.status = status;
            return this;
        }

        public AccountBuilder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        // a new instance on every call, so a builder can be reused safely
        public BankAccount build() {
            BankAccount bankAccount = new BankAccount();
            bankAccount.accountId = accountId;
            bankAccount.balance = balance;
            bankAccount.currency = currency;
            bankAccount.type = type;
            bankAccount.status = status;
            bankAccount.customer = customer;
            return bankAccount;
        }
    }

    @Override
    public BankAccount clone() throws CloneNotSupportedException {
        BankAccount bankAccount = (BankAccount) super.clone();
        if (this.customer != null) {
            bankAccount.setCustomer(this.customer.clone());
        }
        return bankAccount;
    }
}
