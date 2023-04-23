package mx.simio.transaction;

import java.math.BigDecimal;
import java.util.UUID;

public interface TransactionService {

  void createTransaction(int userId, BigDecimal amount, String description);

  void readTransaction(UUID transactionId);

  void readUserTransactions(int userId);

  void sumUserTransactions(int userId);

  void readRandomTransaction();

  void generateReport();
}
