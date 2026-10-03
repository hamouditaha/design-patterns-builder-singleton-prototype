package org.example;

import org.example.model.AccountStatus;
import org.example.model.BankAccount;
import org.example.repository.AccountRepositoryImpl;
import org.example.util.JsonSerializer;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
       // System.out.println("Hello world!");

        /* BankAccount bankAccount = BankDirector.accountBuilder()
                .accountId(1L)
                .currency("MAD")
                .status(AccountStatus.CREATED)
                .type(AccountType.SAVING_ACCOUNT)
                .balance(70000)
                .build();
        System.out.println(bankAccount.toString());*/
        JsonSerializer<BankAccount> bankAccountJsonSerialize = new JsonSerializer<>();



        AccountRepositoryImpl accountRepository = AccountRepositoryImpl.getInstance();

        for(int i =0; i< 10; i++){
             new Thread(() -> {
                 accountRepository.populateDate();
             }
             ).start();
        }
        System.out.println("Tape a character:");
        System.in.read();
        List<BankAccount> bankAccounts = accountRepository
                .searchAccounts(bankAccount -> bankAccount.getStatus().equals(AccountStatus.ACTIVATED));

      //  List<BankAccount> bankAccounts = accountRepository.findAll();

        bankAccounts.stream().map(bankAccountJsonSerialize::toJson)
        .forEach(System.out::println);

      /* System.out.println("====================================");

        BankAccount account = accountRepository.findById(1L).orElse(null);
        if(account != null)
             System.out.println(bankAccountJsonSerialize.toJson(account));
             */
    }
}