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

    // Tính tổng tiền giao dịch theo tháng (month, year)
    public double calculateTotalAmountForMonth(int month, int year) {
        return transactions.stream()
                .filter(t -> t.getDate().getMonthValue() == month && t.getDate().getYear() == year)
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    // Tính tổng tiền giao dịch theo loại mô tả (ví dụ "Salary", "Tesco")
    public double calculateTotalAmountByType(String type) {
        return transactions.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(type))
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }
}