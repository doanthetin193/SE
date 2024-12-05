package main.java.analyzer;

import main.java.model.BankTransaction;
import main.java.parser.BankStatementParser;
import main.java.processor.BankStatementProcessor;
import main.java.reporter.ReportGenerator;
import main.java.util.ErrorHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BankStatementAnalyzer {
    private final BankStatementParser parser;
    private final ReportGenerator reportGenerator = new ReportGenerator();

    public BankStatementAnalyzer(BankStatementParser parser) {
        this.parser = parser;
    }

    public void analyze(String inputFilePath, String outputFilePath, double threshold) {
        try {
            List<String> lines = Files.readAllLines(Path.of(inputFilePath));
            List<BankTransaction> transactions = parser.parseLinesFrom(lines);

            BankStatementProcessor processor = new BankStatementProcessor(transactions);
            System.out.println("Total Amount: " + processor.calculateTotalAmount());
            System.out.println("Transactions above " + threshold + ": " +
                    processor.findTransactionsAboveAmount(threshold));

            reportGenerator.generateHTMLReport(outputFilePath, transactions);
            System.out.println("Report generated at: " + outputFilePath);

        } catch (IOException e) {
            ErrorHandler.logError("Failed to process file", e);
        }
    }
}
