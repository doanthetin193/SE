package main.java;

import main.java.analyzer.BankStatementAnalyzer;
import main.java.parser.BankStatementCSVParser;

import java.io.IOException;

public class MainApplication {
    public static void main(final String... args) throws IOException {
        final BankStatementAnalyzer analyzer = new BankStatementAnalyzer();
        final BankStatementCSVParser parser = new BankStatementCSVParser();
        analyzer.analyze("transactions.csv", parser);
    }
}
