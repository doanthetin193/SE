package main.java.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.model.BankTransaction;

import java.lang.reflect.Type;
import java.util.List;

public class BankStatementJSONParser implements BankStatementParser {
    private final Gson gson = new Gson();

    @Override
    public BankTransaction parseFrom(String line) {
        return gson.fromJson(line, BankTransaction.class);
    }

    @Override
    public List<BankTransaction> parseLinesFrom(List<String> lines) {
        Type listType = new TypeToken<List<BankTransaction>>() {}.getType();
        return gson.fromJson(String.join("", lines), listType);
    }
}
