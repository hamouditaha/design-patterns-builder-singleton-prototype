package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.Customer;

public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount account1 = new BankAccount();
        account1.setAccountId(1L);
        account1.setCurrency("MAD");
        account1.setBalance(6000);
        account1.setType(AccountType.CURRENT_ACCOUNT);
        account1.setStatus(AccountStatus.ACTIVATED);
        account1.setCustomer(new Customer(1L,"Hadjer"));
//        BankAccount account2 = new BankAccount();
//        account2.setAccountId(account1.getAccountId());
//        account2.setCurrency(account1.getCurrency());
//        account2.setType(account1.getType());
//        account2.setBalance(account1.getBalance());
//        account2 .setStatus(account1.getStatus());

         BankAccount account3 = account1.clone();

        System.out.println("ACC1="+account1);
       // System.out.println("ACC2="+account2);
        System.out.println("ACC3="+account3);

//        account1.setBalance(8888);
//        System.out.println(account1.getBalance());
//        System.out.println(account3.getBalance());
          account1.getCustomer().setName("Hhhhhhhhh");
        System.out.println("ACC1="+account1);
        System.out.println("ACC3="+account3);
    }
}
