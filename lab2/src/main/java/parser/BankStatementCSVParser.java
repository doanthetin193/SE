package main.java.parser;

import main.java.model.BankTransaction;
import main.java.util.DateUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
    @Override
    public BankTransaction parseFrom(String line) {
        final String[] columns = line.split(",");
        final LocalDate date = DateUtil.parseDate(columns[0]);  // Sử dụng DateUtil
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];
        return new BankTransaction(date, amount, description);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        List<BankTransaction> bankTransactions = new ArrayList<>();
        for (String line : lines) {
            bankTransactions.add(parseFrom(line));
        }
        return bankTransactions;
    }
}
