package com.bankstatement.service;

import com.bankstatement.model.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BankStatementAnalyzerService {
    private List<Transaction> transactions = new ArrayList<>();

    // Đọc tệp CSV và điền danh sách giao dịch
    public void loadTransactions(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String date = data[0];
                double amount = Double.parseDouble(data[1]);
                String category = data[2];
                transactions.add(new Transaction(date, amount, category));
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Tính tổng lợi nhuận/lỗ
    public double calculateTotalProfitLoss() {
        double total = 0;
        for (Transaction transaction : transactions) {
            total += transaction.getAmount();
        }
        return total;
    }

    // Số lượng giao dịch trong một tháng cụ thể (ví dụ: "02-2017")
    public int countTransactionsInMonth(String monthYear) {
        int count = 0;
        for (Transaction transaction : transactions) {
            // Lấy tháng và năm từ giao dịch
            String month = transaction.getDate().substring(3, 5);  // Lấy tháng từ "DD-MM-YYYY"
            String year = transaction.getDate().substring(6, 10);  // Lấy năm từ "DD-MM-YYYY"
            String monthYearFromTransaction = month + "-" + year;

            // Kiểm tra xem tháng và năm có khớp với giá trị tháng năm cần tìm không
            if (monthYearFromTransaction.equals(monthYear)) {
                count++;
            }
        }
        return count;
    }


    // Nhận 10 khoản chi phí top đầu (số tiền âm)
    public List<Transaction> getTop10Expenses() {
        List<Transaction> expenses = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {
                expenses.add(transaction);
            }
        }
        expenses.sort((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount()));
        return expenses.size() > 10 ? expenses.subList(0, 10) : expenses;
    }

    // Lấy danh mục có số tiền chi tiêu nhiều nhất
    public String getCategoryWithMostSpending() {
        double maxSpending = 0;
        String category = "";

        // Duyệt qua tất cả các giao dịch
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0) {  // Chỉ quan tâm đến chi tiêu (amount âm)
                double spending = getTotalSpendingInCategory(transaction.getCategory());
                if (spending < 0 && spending < maxSpending) {  // Tính toán chi tiêu âm
                    maxSpending = spending;
                    category = transaction.getCategory();
                }
            }
        }
        return category.isEmpty() ? "No expenses" : category;
    }

    private double getTotalSpendingInCategory(String category) {
        double totalSpending = 0;
        // Duyệt qua tất cả các giao dịch và cộng dồn chi tiêu âm của danh mục tương ứng
        for (Transaction transaction : transactions) {
            if (transaction.getCategory().equals(category) && transaction.getAmount() < 0) {  // Chỉ tính chi tiêu âm
                totalSpending += transaction.getAmount();
            }
        }
        return totalSpending;
    }

    // In ra tất cả các giao dịch
    public void printTransactions() {
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}
        