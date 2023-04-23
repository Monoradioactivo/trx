package mx.simio.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {

  private final UUID id;
  private int userId;
  private BigDecimal amount;
  private String description;
  private LocalDate createdAt;

  public Transaction(int userId, BigDecimal amount, String description) {
    this.id = UUID.randomUUID();
    this.userId = userId;
    this.amount = amount;
    this.description = description;
    this.createdAt = LocalDate.now();
  }

  public UUID getId() {
    return id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "id=" + id +
        ", userId=" + userId +
        ", amount=" + amount +
        ", description='" + description + '\'' +
        ", createdAt=" + createdAt +
        '}';
  }
}
