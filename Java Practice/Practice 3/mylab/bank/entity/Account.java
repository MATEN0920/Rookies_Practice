package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

public class Account {
    private String accountNumber;
    private String ownerName;
    protected double balance;

    public Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }

    public void deposit(double amount) {
        if (amount <= 0) return;
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) return;
        if (balance < amount) {
            throw new InsufficientBalanceException("잔액이 부족합니다.");
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원",
                accountNumber, ownerName, balance);
    }
}