package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {
    private List<Account> accounts;
    private int nextAccountNumber;

    public Bank() {
        this.accounts = new ArrayList<>();
        this.nextAccountNumber = 1000;
    }

    private String generateAccountNumber() {
        return "AC" + (nextAccountNumber++);
    }

    public String createSavingsAccount(String ownerName, double initialBalance, double interestRate) {
        String accNo = generateAccountNumber();
        Account acc = new SavingsAccount(accNo, ownerName, initialBalance, interestRate);
        accounts.add(acc);
        return accNo;
    }

    public String createCheckingAccount(String ownerName, double initialBalance, double withdrawalLimit) {
        String accNo = generateAccountNumber();
        Account acc = new CheckingAccount(accNo, ownerName, initialBalance, withdrawalLimit);
        accounts.add(acc);
        return accNo;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account a : accounts) {
            if (a.getAccountNumber().equals(accountNumber)) return a;
        }
        throw new AccountNotFoundException(
                String.format("계좌번호 %s에 해당하는 계좌를 찾을 수 없습니다.", accountNumber)
        );
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account acc = findAccount(accountNumber);
        acc.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account acc = findAccount(accountNumber);
        acc.withdraw(amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account from = findAccount(fromAccountNumber);
        Account to = findAccount(toAccountNumber);

        from.withdraw(amount);
        to.deposit(amount);
    }

    public void printAllAccounts() {
        for (Account a : accounts) {
            System.out.println(a);
        }
    }
}