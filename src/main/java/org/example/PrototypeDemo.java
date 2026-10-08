package org.example;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirector;
import org.example.model.Customer;

public class PrototypeDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        BankAccount account1 = BankDirector.accountBuilder()
                .accountId(1L)
                .currency("MAD")
                .balance(6000)
                .type(AccountType.CURRENT_ACCOUNT)
                .status(AccountStatus.ACTIVATED)
                .customer(new Customer(1L, "Hadjer"))
                .build();

        BankAccount account2 = account1.clone();
        System.out.println("ACC1 = " + account1);
        System.out.println("ACC2 = " + account2);

        // deep copy: changing the original customer does not affect the clone
        account1.getCustomer().setName("Modified");
        System.out.println("ACC1 = " + account1);
        System.out.println("ACC2 = " + account2);
    }
}
