package main.java.processor;

import main.java.model.BankTransaction;

import java.util.List;
import java.util.stream.Collectors;

public class BankStatementProcessor {
    private final List<BankTransaction> transactions;

    public BankStatementProcessor(List<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public double calculateTotalAmount() {
        return transactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    public List<BankTransaction> findTransactionsAboveAmount(double amount) {
        return transactions.stream()
                .filter(t -> t.getAmount() > amount)
                .collect(Collectors.toList());
    }
}
