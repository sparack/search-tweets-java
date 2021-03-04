package dev.twitter.api.v2.util;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

  private static DateTimeFormatter dtf = DateTimeFormatter.ISO_INSTANT;

  public static String toISO(ZonedDateTime zdt) {
    return zdt.format(dtf);
  }
}
