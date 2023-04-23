package mx.simio.transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {

  Transaction createTransaction(int userId, BigDecimal amount, String description);

  Transaction readTransaction(UUID transactionId);

  List<Transaction> readUserTransactions(int userId);

  BigDecimal sumUserTransactions(int userId);

  Transaction readRandomTransaction();

  List<TransactionReport> generateReport();
}
