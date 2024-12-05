package main.java.parser;

import main.java.model.BankTransaction;
import main.java.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {
    @Override
    public BankTransaction parseFrom(String line) {
        String[] columns = line.split(",");
        return new BankTransaction(DateUtil.parseDate(columns[0]),
                Double.parseDouble(columns[1]),
                columns[2]);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        List<BankTransaction> transactions = new ArrayList<>();
        for (String line : lines) {
            transactions.add(parseFrom(line));
        }
        return transactions;
    }
}
