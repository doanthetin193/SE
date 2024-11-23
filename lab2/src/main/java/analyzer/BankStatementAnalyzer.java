package main.java.analyzer;

import main.java.model.BankTransaction;
import main.java.parser.BankStatementParser;
import main.java.processor.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public class BankStatementAnalyzer {
    private static final String RESOURCES = "resources/";

    public void analyze(final String fileName, final BankStatementParser parser) throws IOException {
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        final List<BankTransaction> bankTransactions = parser.parseLinesFrom(lines);
        final BankStatementProcessor processor = new BankStatementProcessor(bankTransactions);
        collectSummary(processor);
    }

    private void collectSummary(final BankStatementProcessor processor) {
        System.out.println("The total for all transactions is: " + processor.calculateTotalAmount());
        System.out.println("The total for transactions in January is: " + processor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("The total salary received is: " + processor.calculateTotalForCategory("Salary"));
    }
}
