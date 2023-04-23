package mx.simio;

import java.util.Scanner;
import java.util.UUID;
import mx.simio.transaction.TransactionService;
import mx.simio.transaction.TransactionServiceImpl;

public class Main {

  public static void main(String[] args) {
    TransactionService transactionService = new TransactionServiceImpl();
    Scanner scanner = new Scanner(System.in);
    int option = 0;

    while (option != 7) {
      System.out.println("""
          Select the operation you want to perform:
          1. Create Transaction
          2. Read transaction
          3. Read user transactions
          4. Sum user transactions
          5. Read a random transaction
          6. Generate report
          7. Exit
          """);

      option = scanner.nextInt();

      if (option < 1 || option > 7) {
        System.out.println("Invalid option");
      } else {
        switch (option) {
          case 1 -> {
            System.out.println("Please enter the user id: ");
            var userId = scanner.nextInt();
            System.out.println("Please enter the amount: ");
            var amount = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.println("Please enter the description: ");
            var description = scanner.nextLine();
            transactionService.createTransaction(userId, amount, description);
          }
          case 2 -> {
            System.out.println("Please enter the transaction id: ");
            var transactionId = scanner.next();
            UUID transactionUUID = UUID.fromString(transactionId);
            transactionService.readTransaction(transactionUUID);
          }
          case 3 -> {
            System.out.println("Please enter the user id: ");
            var userId = scanner.nextInt();
            transactionService.readUserTransactions(userId);
          }
          case 4 -> {
            System.out.println("Please enter the user id: ");
            var userId = scanner.nextInt();
            transactionService.sumUserTransactions(userId);
          }
          case 5 -> transactionService.readRandomTransaction();
          case 6 -> transactionService.generateReport();
          case 7 -> System.out.println("Bye");
        }
      }
    }
    scanner.close();
  }
}