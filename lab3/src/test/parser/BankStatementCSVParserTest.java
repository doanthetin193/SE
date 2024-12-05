package test.parser;

import main.java.parser.BankStatementParser;
import org.junit.Assert;
import org.junit.Test;
import main.java.parser.BankStatementCSVParser;
import main.java.model.BankTransaction;
import java.time.LocalDate;
import java.time.Month;

public class BankStatementCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception {
        final String line = "30-01-2017,-50,Tesco";

        final BankTransaction result = statementParser.parseFrom(line);

        final BankTransaction expected = new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), 0.01);  // Tolerance: 0.01
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseMultipleLines() throws Exception {
        final String line1 = "30-01-2017,-50,Tesco";
        final String line2 = "31-01-2017,200,Salary";

        final BankTransaction result1 = statementParser.parseFrom(line1);
        final BankTransaction result2 = statementParser.parseFrom(line2);

        Assert.assertNotNull(result1);
        Assert.assertNotNull(result2);
    }
}
