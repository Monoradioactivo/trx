package mx.simio.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import mx.simio.transaction.Transaction;

public class TrxMixin {

  private final Random random = new Random();

  /**
   * Creates a Map of random transactions.
   *
   * @return the Map of transactions
   */
  public Map<UUID, Transaction> generateTransactions() {
    Map<UUID, Transaction> transactions = new HashMap<>();
    int i = 1;
    int hits = 0;
    while (i < 5) {
      Transaction transaction = new Transaction(i,
          BigDecimal.valueOf(random.nextInt((100 - 10) + 1) + 10), "Transaction " + i);
      transaction.setCreatedAt(generateRandomDate());
      transactions.put(transaction.getId(), transaction);
      hits++;
      if (hits == 5) {
        i++;
        hits = 0;
      }
    }

    return transactions;
  }

  /**
   * Generates a random date between 2023-01-01 and 2023-04-01.
   *
   * @return the random date
   */
  private LocalDate generateRandomDate() {
    LocalDate startDate = LocalDate.of(2023, 1, 1);
    LocalDate endDate = LocalDate.of(2023, 4, 1);

    long startEpochDay = startDate.toEpochDay();
    long endEpochDay = endDate.toEpochDay();
    long randomEpochDay = ThreadLocalRandom.current().nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomEpochDay);
  }
}
