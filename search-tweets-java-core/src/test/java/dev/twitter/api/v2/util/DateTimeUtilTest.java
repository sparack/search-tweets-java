package dev.twitter.api.v2.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class DateTimeUtilTest {

  @Test
  public void testToISO() {
    LocalDate ld = LocalDate.of(2021, 2, 1);
    ZonedDateTime zdt = ZonedDateTime.of(ld, LocalTime.MIN, ZoneId.of("Asia/Singapore"));
    assertEquals("2021-01-31T16:00:00Z", DateTimeUtil.toISO(zdt));
  }
}
