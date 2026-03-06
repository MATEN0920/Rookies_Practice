package mylab.bank.control;

import mylab.bank.entity.Bank;
import mylab.bank.entity.Account;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class BankDemo {

    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        String ac1000 = bank.createSavingsAccount("홍길동", 10000.0, 3.0);
        System.out.println("Saving(저축) 계좌가 생성되었습니다: 계좌번호: " + ac1000
                + ", 소유자: 홍길동, 잔액: 10000.0원, 이자율: 3.0%");

        String ac1001 = bank.createCheckingAccount("김철수", 20000.0, 5000.0);
        System.out.println("체킹 계좌가 생성되었습니다: 계좌번호: " + ac1001
                + ", 소유자: 김철수, 잔액: 20000.0원, 출금 한도: 5000.0원");

        String ac1002 = bank.createSavingsAccount("이영희", 30000.0, 2.0);
        System.out.println("저축 계좌가 생성되었습니다: 계좌번호: " + ac1002
                + ", 소유자: 이영희, 잔액: 30000.0원, 이자율: 2.0%");

        System.out.println();

        System.out.println("=== 모든 계좌 목록 ===");
        bank.printAllAccounts();
        System.out.println("===================");
        System.out.println();

        System.out.println("=== 입금/출금 테스트 ===");
        try {
            bank.deposit(ac1000, 5000.0);
            double bal1 = bank.findAccount(ac1000).getBalance();
            System.out.println("5000.0원이 입금되었습니다. 현재 잔액: " + bal1 + "원");

            bank.withdraw(ac1001, 3000.0);
            double bal2 = bank.findAccount(ac1000).getBalance();
            System.out.println("3000.0원이 출금되었습니다. 현재 잔액: " + bal2 + "원");
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println();

        System.out.println("=== 이자 적용 테스트 ===");
        try {
            Account a = bank.findAccount(ac1000);
            if (a instanceof SavingsAccount) {
                SavingsAccount sa = (SavingsAccount) a;

                double before = sa.getBalance();
                sa.applyInterest();
                double after = sa.getBalance();
                double interest = after - before;

                System.out.println(interest + "원이 입금되었습니다. 현재 잔액: " + after + "원");
                System.out.println("이자 " + interest + "원이 적용되었습니다. 현재 잔액: " + after + "원");
            }
        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println();

        System.out.println("=== 계좌 이체 테스트 ===");
        try {
            bank.withdraw(ac1002, 5000.0);
            double fromBalAfterWithdraw = bank.findAccount(ac1002).getBalance();
            System.out.println("5000.0원이 출금되었습니다. 현재 잔액: " + fromBalAfterWithdraw + "원");

            bank.deposit(ac1001, 5000.0);
            double toBalAfterDeposit = bank.findAccount(ac1001).getBalance();
            System.out.println("5000.0원이 입금되었습니다. 현재 잔액: " + toBalAfterDeposit + "원");

            System.out.println("5000.0원이 " + ac1002 + "에서 " + ac1001 + "로 송금되었습니다.");

        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println();
        
        System.out.println("=== 모든 계좌 목록 ===");
        bank.printAllAccounts();
        System.out.println("===================");
        

        try {
            bank.withdraw(ac1001, 6000.0);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.withdraw(ac1001, 7000.0);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.withdraw("AC9999", 1000.0);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}