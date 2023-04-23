package mx.simio.transaction;

import java.math.BigDecimal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TransactionServiceImplTest {

  private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    transactionService = new TransactionServiceImpl();
  }

  @Test
  void testCreateTransaction() {
    int userId = 1;
    BigDecimal amount = BigDecimal.valueOf(42);
    String description = "Test transaction";
    Transaction transaction = transactionService.createTransaction(userId, amount, description);

    assertNotNull(transaction, "Transaction should not be null");
    assertEquals(userId, transaction.getUserId(), "User ID should match");
    assertEquals(amount, transaction.getAmount(), "Amount should match");
    assertEquals(description, transaction.getDescription(), "Description should match");

    Transaction storedTransaction = transactionService.readTransaction(
        transaction.getId());
    assertEquals(transaction, storedTransaction);
  }

  @Test
  void testReadTransaction() {
    int userId = 1;
    BigDecimal amount = BigDecimal.valueOf(42);
    String description = "Test transaction";
    Transaction existingTransaction = transactionService.createTransaction(userId, amount, description);

    Transaction foundTransaction = transactionService.readTransaction(existingTransaction.getId());
    assertNotNull(foundTransaction);
    assertEquals(existingTransaction, foundTransaction);

    UUID nonExistingId = UUID.randomUUID();
    Transaction notFoundTransaction = transactionService.readTransaction(nonExistingId);
    assertNull(notFoundTransaction);
  }

  @Test
  void testReadUserTransactions() {

    List<Transaction> user1Transactions = transactionService.readUserTransactions(1);
    assertEquals(5, user1Transactions.size()); //5 because of the 5 transactions in the Map

    List<Transaction> nonExistingUserTransactions = transactionService.readUserTransactions(10);
    assertTrue(nonExistingUserTransactions.isEmpty());
  }

  @Test
  void testSumUserTransactions() {
    transactionService.createTransaction(10, BigDecimal.valueOf(100), "Transaction 1 for user 1");
    transactionService.createTransaction(10, BigDecimal.valueOf(200), "Transaction 2 for user 1");
    transactionService.createTransaction(20, BigDecimal.valueOf(300), "Transaction 1 for user 2");

    BigDecimal userTransactionsSum = transactionService.sumUserTransactions(10);
    assertEquals(BigDecimal.valueOf(300), userTransactionsSum);

    BigDecimal nonExistingUserTransactionSum = transactionService.sumUserTransactions(30);
    assertEquals(BigDecimal.ZERO, nonExistingUserTransactionSum);
  }

  @Test
  void testGenerateReport() {
    transactionService.createTransaction(10, BigDecimal.valueOf(100), "Transaction 1 for user 1");
    transactionService.createTransaction(10, BigDecimal.valueOf(200), "Transaction 2 for user 1");
    transactionService.createTransaction(20, BigDecimal.valueOf(300), "Transaction 1 for user 2");

    List<TransactionReport> reports = transactionService.generateReport();
    assertNotNull(reports);

    LocalDate today = LocalDate.now();
    LocalDate weekStartDate = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
    LocalDate weekEndDate = weekStartDate.plusDays(6);

    TransactionReport currentWeekReport = reports.stream()
        .filter(report -> report.getWeekStartDate().isEqual(weekStartDate))
        .findFirst()
        .orElse(null);

    assertNotNull(currentWeekReport);
    assertEquals(3, currentWeekReport.getQuantity());
    assertEquals(weekStartDate, currentWeekReport.getWeekStartDate());
    assertEquals(weekEndDate, currentWeekReport.getWeekFinishDate());
  }

  @Test
  void testReadRandomTransaction() {
    Transaction randomTransaction = transactionService.readRandomTransaction();
    assertNotNull(randomTransaction);
  }

}
