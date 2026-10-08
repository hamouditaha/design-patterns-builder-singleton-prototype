package org.example;

import org.example.model.AccountStatus;
import org.example.model.BankAccount;
import org.example.model.BankDirector;
import org.example.model.Customer;
import org.example.repository.AccountRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PatternsTest {

    @Test
    void builderCreatesANewInstanceOnEachBuild() {
        BankAccount.AccountBuilder builder = BankDirector.accountBuilder().currency("MAD").balance(100);
        BankAccount first = builder.build();
        BankAccount second = builder.balance(999).build();

        assertNotSame(first, second);
        assertEquals(100, first.getBalance());
        assertEquals(999, second.getBalance());
    }

    @Test
    void singletonReturnsTheSameInstance() {
        assertSame(AccountRepositoryImpl.getInstance(), AccountRepositoryImpl.getInstance());
    }

    @Test
    void concurrentSavesLoseNoAccount() throws InterruptedException {
        AccountRepositoryImpl repository = AccountRepositoryImpl.getInstance();
        int before = repository.findAll().size();

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(repository::populateData);
            threads.add(thread);
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(before + 100, repository.findAll().size());
    }

    @Test
    void cloneIsADeepCopy() throws CloneNotSupportedException {
        BankAccount original = BankDirector.accountBuilder()
                .status(AccountStatus.ACTIVATED)
                .customer(new Customer(1L, "Hadjer"))
                .build();
        BankAccount copy = original.clone();

        original.getCustomer().setName("Modified");

        assertNotSame(original.getCustomer(), copy.getCustomer());
        assertEquals("Hadjer", copy.getCustomer().getName());
    }

    @Test
    void cloneWorksWithoutCustomer() throws CloneNotSupportedException {
        BankAccount copy = BankDirector.accountBuilder().build().clone();
        assertNull(copy.getCustomer());
    }

    @Test
    void setIdUpdatesTheCustomer() {
        Customer customer = new Customer(1L, "A");
        customer.setId(42L);
        assertEquals(42L, customer.getId());
    }
}
