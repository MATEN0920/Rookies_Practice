package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class CheckingAccount extends Account {
    private double withdrawalLimit;

    public CheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
        super(accountNumber, ownerName, balance);
        this.withdrawalLimit = withdrawalLimit;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) return;

        if (amount > withdrawalLimit) {
            throw new WithdrawalLimitExceededException(
                    String.format("출금 한도를 초과했습니다. 한도: %.1f원", withdrawalLimit)
            );
        }

        // 잔액 부족 검사는 부모 로직 사용
        super.withdraw(amount);
    }

    @Override
    public String toString() {
        return String.format("계좌번호: %s, 소유자: %s, 잔액: %.1f원, 출금 한도: %.1f원",
                getAccountNumber(), getOwnerName(), getBalance(), withdrawalLimit);
    }
}