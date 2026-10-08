package org.example;

import org.example.model.AccountStatus;
import org.example.model.BankAccount;
import org.example.repository.AccountRepositoryImpl;
import org.example.util.JsonSerializer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JsonSerializer<BankAccount> bankAccountJsonSerializer = new JsonSerializer<>();
        AccountRepositoryImpl accountRepository = AccountRepositoryImpl.getInstance();

        // 10 threads share the same Singleton repository
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(accountRepository::populateData);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Total accounts: " + accountRepository.findAll().size());

        List<BankAccount> bankAccounts = accountRepository
                .searchAccounts(bankAccount -> bankAccount.getStatus() == AccountStatus.ACTIVATED);

        bankAccounts.stream()
                .map(bankAccountJsonSerializer::toJson)
                .forEach(System.out::println);
    }
}
