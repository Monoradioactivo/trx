package mx.simio.util;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;
import mx.simio.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


class TrxMixinTest {

  private TrxMixin trxMixin;

  @BeforeEach
  void setUp() {
    trxMixin = new TrxMixin();
  }

  @Test
  void generateTransactions_verifySize() {
    Map<UUID, Transaction> transactions = trxMixin.generateTransactions();
    assertEquals(20, transactions.size());
  }

  @Test
  void testGenerateTransactions_uniqueIds() {
    Map<UUID, Transaction> transactions = trxMixin.generateTransactions();
    assertEquals(20, transactions.values().stream().map(Transaction::getId).distinct().count(),
        "Generated transactions should have 20 unique IDs");
  }

  @Test
  void testGenerateTransactions_amountRange() {
    Map<UUID, Transaction> transactions = trxMixin.generateTransactions();
    transactions.values().forEach(transaction -> {
      BigDecimal amount = transaction.getAmount();
      assertNotNull(amount, "Transaction amount should not be null");
      assertTrue(amount.compareTo(BigDecimal.valueOf(10)) >= 0,
          "Transaction amount should be greater than or equal to 10");
      assertTrue(amount.compareTo(BigDecimal.valueOf(100)) <= 0,
          "Transaction amount should be less than or equal to 100");
    });
  }

  @Test
  void testGenerateTransactions_description() {
    Map<UUID, Transaction> transactions = trxMixin.generateTransactions();
    transactions.values().forEach(transaction -> {
      String description = transaction.getDescription();
      assertNotNull(description, "Transaction description should not be null");
      assertTrue(description.startsWith("Transaction "),
          "Transaction description should start with 'Transaction '");
    });
  }

  @Test
  void testGenerateTransactions_createdAt() {
    Map<UUID, Transaction> transactions = trxMixin.generateTransactions();
    transactions.values().forEach(transaction -> {
      assertNotNull(transaction.getCreatedAt(), "Transaction createdAt should not be null");
    });
  }
}
