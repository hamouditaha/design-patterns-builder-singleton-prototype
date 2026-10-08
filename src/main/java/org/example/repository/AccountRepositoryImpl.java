package org.example.repository;

import org.example.model.AccountStatus;
import org.example.model.AccountType;
import org.example.model.BankAccount;
import org.example.model.BankDirector;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Predicate;

public class AccountRepositoryImpl implements AccountRepository {

    // eager initialization: the JVM creates the instance once, when the class is loaded (thread-safe)
    private static final AccountRepositoryImpl INSTANCE = new AccountRepositoryImpl();

    // thread-safe structures: several threads write and read concurrently
    private final Map<Long, BankAccount> bankAccountMap = new ConcurrentHashMap<>();
    private final AtomicLong accountsCount = new AtomicLong();

    private AccountRepositoryImpl() {
        System.out.println("Singleton Instantiation");
    }

    public static AccountRepositoryImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public BankAccount save(BankAccount bankAccount) {
        long accountId = accountsCount.incrementAndGet();
        bankAccount.setAccountId(accountId);
        bankAccountMap.put(accountId, bankAccount);
        return bankAccount;
    }

    @Override
    public List<BankAccount> findAll() {
        return List.copyOf(bankAccountMap.values());
    }

    @Override
    public Optional<BankAccount> findById(Long id) {
        return Optional.ofNullable(bankAccountMap.get(id));
    }

    @Override
    public List<BankAccount> searchAccounts(Predicate<BankAccount> predicate) {
        return bankAccountMap.values().stream().filter(predicate).toList();
    }

    @Override
    public BankAccount update(BankAccount bankAccount) {
        bankAccountMap.put(bankAccount.getAccountId(), bankAccount);
        return bankAccount;
    }

    @Override
    public void deleteById(Long id) {
        bankAccountMap.remove(id);
    }

    public void populateData() {
        for (int i = 0; i < 10; i++) {
            BankAccount bankAccount = BankDirector.accountBuilder()
                    .balance(10000 + Math.random() * 90000)
                    .type(Math.random() > 0.5 ? AccountType.SAVING_ACCOUNT : AccountType.CURRENT_ACCOUNT)
                    .status(Math.random() > 0.5 ? AccountStatus.CREATED : AccountStatus.ACTIVATED)
                    .currency(Math.random() > 0.5 ? "MAD" : "USD")
                    .build();
            save(bankAccount);
        }
        System.out.println(Thread.currentThread().getName() + " -> accounts: " + accountsCount.get());
    }
}
