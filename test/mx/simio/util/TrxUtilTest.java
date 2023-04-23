package mx.simio.util;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrxUtilTest {

  @Test
  void testGetWeekStartDate_firstDayOfMonth() {
    LocalDate date = LocalDate.of(2023, 5, 1);
    LocalDate weekStartDate = TrxUtil.getWeekStartDate(date);
    assertEquals(date, weekStartDate);
  }

  @Test
  void testGetWeekStartDate_firstDayOfMonthNotMonday() {
    LocalDate date = LocalDate.of(2023, 4, 1);
    LocalDate weekStartDate = TrxUtil.getWeekStartDate(date);
    assertEquals(LocalDate.of(2023, 4, 3), weekStartDate);
  }

  @Test
  void testGetWeekStartDate_withinMonth() {
    LocalDate date = LocalDate.of(2023, 4, 15);
    LocalDate weekStartDate = TrxUtil.getWeekStartDate(date);
    assertEquals(LocalDate.of(2023, 4, 10), weekStartDate);
  }

  @Test
  void testGetWeekStartDate_onMonday() {
    LocalDate date = LocalDate.of(2023, 4, 17);
    LocalDate weekStartDate = TrxUtil.getWeekStartDate(date);
    assertEquals(date, weekStartDate);
  }
}
