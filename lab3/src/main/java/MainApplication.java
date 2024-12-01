package main.java;

import main.java.analyzer.BankStatementAnalyzer;
import main.java.parser.BankStatementCSVParser;

public class MainApplication {
    public static void main(String[] args) {
        BankStatementAnalyzer analyzer = new BankStatementAnalyzer(new BankStatementCSVParser());
        analyzer.analyze("resources/transactions.csv", "resources/report.html", 500.0);
    }
}
