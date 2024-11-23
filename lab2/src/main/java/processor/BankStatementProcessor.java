package main.java.processor;

import main.java.model.BankTransaction;

import java.time.Month;
import java.util.List;

public class BankStatementProcessor {
    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        double total = 0;
        for (final BankTransaction transaction : bankTransactions) {
            total += transaction.getAmount();
        }
        return total;
    }

    public double calculateTotalInMonth(final Month month) {
        double total = 0;
        for (final BankTransaction transaction : bankTransactions) {
            if (transaction.getDate().getMonth() == month) {
                total += transaction.getAmount();
            }
        }
        return total;
    }

    public double calculateTotalForCategory(final String category) {
        double total = 0;
        for (final BankTransaction transaction : bankTransactions) {
            if (transaction.getDescription().equalsIgnoreCase(category)) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
}
