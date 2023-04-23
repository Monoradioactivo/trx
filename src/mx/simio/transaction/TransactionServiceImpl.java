package mx.simio.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import mx.simio.util.TrxMixin;
import mx.simio.util.TrxUtil;

public class TransactionServiceImpl implements TransactionService {

  private final TrxMixin trxMixin = new TrxMixin();

  Map<UUID, Transaction> transactions = trxMixin.generateTransactions();

  /**
   * Creates a transaction.
   *
   * @param userId      The user id
   * @param amount      The amount
   * @param description The description
   */
  @Override
  public void createTransaction(int userId, BigDecimal amount, String description) {
    Transaction transaction = new Transaction(userId, amount, description);
    transactions.put(transaction.getId(), transaction);

    System.out.println("Transaction created: " + transaction);
  }

  /**
   * Reads a transaction given its UUID.
   *
   * @param transactionId The transaction id
   */
  @Override
  public void readTransaction(UUID transactionId) {
    transactions.forEach((k, v) -> {
      if (v.getId().equals(transactionId)) {
        System.out.println("Transaction found: " + v);
      } else {
        System.out.printf("Transaction with id %s not found", transactionId);
      }
    });
  }

  /**
   * Reads all transactions for a given user.
   *
   * @param userId The user id
   */
  @Override
  public void readUserTransactions(int userId) {
    List<Transaction> userTransactions = new ArrayList<>();
    transactions.forEach((k, v) -> {
      if (v.getUserId() == userId) {
        userTransactions.add(v);
      }
    });

    if (userTransactions.isEmpty()) {
      System.out.printf("No transactions found for user %d", userId);
    }

    System.out.println("User transactions: " + userTransactions);
  }

  /**
   * Sums all transactions for a given user.
   *
   * @param userId The user id
   */
  @Override
  public void sumUserTransactions(int userId) {
    List<BigDecimal> amounts = new ArrayList<>();
    transactions.forEach((k, v) -> {
      if (v.getUserId() == userId) {
        amounts.add(v.getAmount());
      }
    });

    BigDecimal sum = amounts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);

    System.out.println("User transactions sum: " + sum);
  }

  /**
   * Reads a random transaction.
   */
  @Override
  public void readRandomTransaction() {
    List<Transaction> transactionList = new ArrayList<>(transactions.values());
    Collections.shuffle(transactionList);
    Optional<Transaction> randomTransaction = transactionList.stream().findFirst();

    if (randomTransaction.isEmpty()) {
      System.out.println("No transaction found");
    } else {
      System.out.println("Random transaction: " + randomTransaction.get());
    }

  }

  /**
   * Generates a report of transactions per week.
   */
  @Override
  public void generateReport() {
    List<Transaction> transactionList = new ArrayList<>(transactions.values());
    Map<LocalDate, List<Transaction>> transactionsByWeek = transactionList.stream()
        .collect(Collectors.groupingBy(
            transaction -> TrxUtil.getWeekStartDate(transaction.getCreatedAt())));

    List<TransactionReport> reports = transactionsByWeek.entrySet().stream()
        .filter(entry -> !entry.getValue().isEmpty())
        .map(entry -> new TransactionReport(
            entry.getKey(),
            entry.getKey().plusDays(6),
            entry.getValue().size()
        ))
        .sorted(Comparator.comparing(TransactionReport::getWeekStartDate))
        .collect(Collectors.toList());

    System.out.println(reports);
  }
}
