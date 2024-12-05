package test.processor;

import main.java.processor.BankStatementProcessor;
import main.java.model.BankTransaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class BankStatementProcessorTest {

    private BankStatementProcessor processor;

    @Before
    public void setUp() {
        // Tạo danh sách các giao dịch mẫu
        List<BankTransaction> transactions = Arrays.asList(
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco"),
                new BankTransaction(LocalDate.of(2017, Month.FEBRUARY, 15), 200, "Salary"),
                new BankTransaction(LocalDate.of(2017, Month.JANUARY, 20), 100, "Salary"),
                new BankTransaction(LocalDate.of(2017, Month.MARCH, 10), -150, "Tesco")
        );

        processor = new BankStatementProcessor(transactions);
    }

    @Test
    public void shouldCalculateTotalAmount() {
        double result = processor.calculateTotalAmount();
        double expected = 100.0; // Tổng tiền là -50 + 200 + 100 + (-150) = 100
        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void shouldCalculateTotalAmountForMonth() {
        double result = processor.calculateTotalAmountForMonth(1, 2017); // Tháng 1, năm 2017
        double expected = 50.0; // Tổng tiền trong tháng 1 là -50 + 100 = 50
        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void shouldCalculateTotalAmountForMonthFebruary() {
        double result = processor.calculateTotalAmountForMonth(2, 2017); // Tháng 2, năm 2017
        double expected = 200.0; // Tổng tiền trong tháng 2 là 200 (Salary)
        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void shouldCalculateTotalAmountByType() {
        double result = processor.calculateTotalAmountByType("Salary");
        double expected = 300.0; // Tổng tiền của loại "Salary" là 200 + 100 = 300
        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void shouldCalculateTotalAmountByTypeTesco() {
        double result = processor.calculateTotalAmountByType("Tesco");
        double expected = -200.0; // Tổng tiền của loại "Tesco" là -50 + (-150) = -200
        Assert.assertEquals(expected, result, 0.01);
    }

    @Test
    public void shouldFindTransactionsAboveAmount() throws Exception {
        // Tạo danh sách giao dịch
        List<BankTransaction> transactions = List.of(
                new BankTransaction(LocalDate.of(2023, Month.MAY, 1), -50, "Tesco"),
                new BankTransaction(LocalDate.of(2023, Month.MAY, 5), 200, "Salary"),
                new BankTransaction(LocalDate.of(2023, Month.MAY, 10), -20, "Coffee"),
                new BankTransaction(LocalDate.of(2023, Month.MAY, 15), 150, "Freelance Work")
        );

        // Khởi tạo đối tượng processor
        BankStatementProcessor processor = new BankStatementProcessor(transactions);

        // Tìm các giao dịch có số tiền lớn hơn 100
        List<BankTransaction> result = processor.findTransactionsAboveAmount(100);

        // Kiểm tra số lượng giao dịch phù hợp
        Assert.assertEquals(2, result.size());

        // Kiểm tra các giao dịch có số tiền lớn hơn 100
        Assert.assertTrue(result.contains(transactions.get(1))); // Salary
        Assert.assertTrue(result.contains(transactions.get(3))); // Freelance Work
    }

}
