package main.java.reporter;

import main.java.model.BankTransaction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {
    public void generateHTMLReport(String filePath, List<BankTransaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("<html><body><h1>Bank Transactions Report</h1><ul>");
            for (BankTransaction transaction : transactions) {
                writer.write("<li>" + transaction + "</li>");
            }
            writer.write("</ul></body></html>");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write HTML report.", e);
        }
    }
}
