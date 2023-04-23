package mx.simio.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class TrxUtil {

  private TrxUtil() {
    throw new IllegalStateException("Utility class");
  }

  public static LocalDate getWeekStartDate(LocalDate date) {
    if (date.getDayOfMonth() == 1) {
      return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
    }
    return date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
  }
}
