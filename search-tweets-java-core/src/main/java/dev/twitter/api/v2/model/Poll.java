package dev.twitter.api.v2.model;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Poll {
  private String id;
  private List<Map<String,Object>> options;
  private String votingStatus; // could be enum if we know values
  private int durationMinutes;
  private ZonedDateTime endDatetime;
}
