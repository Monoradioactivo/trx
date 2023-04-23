package mx.simio.transaction;

import java.time.LocalDate;

public class TransactionReport {
  private LocalDate weekStartDate;
  private LocalDate weekFinishDate;
  private int quantity;

  public TransactionReport(LocalDate weekStartDate, LocalDate weekFinishDate, int quantity) {
    this.weekStartDate = weekStartDate;
    this.weekFinishDate = weekFinishDate;
    this.quantity = quantity;
  }

  public LocalDate getWeekStartDate() {
    return weekStartDate;
  }

  public void setWeekStartDate(LocalDate weekStartDate) {
    this.weekStartDate = weekStartDate;
  }

  public LocalDate getWeekFinishDate() {
    return weekFinishDate;
  }

  public void setWeekFinishDate(LocalDate weekFinishDate) {
    this.weekFinishDate = weekFinishDate;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public String toString() {
    return "TransactionReport{" +
        ", weekStartDate=" + weekStartDate +
        ", weekFinishDate=" + weekFinishDate +
        ", quantity=" + quantity +
        '}';
  }
}
