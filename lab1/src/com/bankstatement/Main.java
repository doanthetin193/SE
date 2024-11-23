package com.bankstatement;

import com.bankstatement.model.Transaction;
import com.bankstatement.service.BankStatementAnalyzerService;

public class Main {
    public static void main(String[] args) {
        // Tạo đối tượng để phân tích dữ liệu giao dịch ngân hàng
        BankStatementAnalyzerService analyzer = new BankStatementAnalyzerService();

        // Đọc file CSV
        analyzer.loadTransactions("resources/transactions.csv");

        // In ra danh sách giao dịch
        System.out.println("All Transactions:");
        analyzer.printTransactions();

        // Tính tổng lợi nhuận/lỗ
        double totalProfitLoss = analyzer.calculateTotalProfitLoss();
        System.out.println("Total Profit/Loss: " + totalProfitLoss);

        // Tính số giao dịch trong tháng 02-2017
        int transactionsInFeb = analyzer.countTransactionsInMonth("02-2017");
        System.out.println("Number of transactions in February 2017: " + transactionsInFeb);

        // In top 10 chi tiêu
        System.out.println("Top 10 Expenses:");
        for (Transaction transaction : analyzer.getTop10Expenses()) {
            System.out.println(transaction);
        }


        // Tìm loại chi tiêu nhiều nhất
        String categoryWithMostSpending = analyzer.getCategoryWithMostSpending();
        System.out.println("Category with most spending: " + categoryWithMostSpending);
    }
}
