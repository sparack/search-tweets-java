package dev.twitter.api.v2.model.stream;

import java.util.List;
import java.util.Map;

import dev.twitter.api.v2.model.Includes;
import dev.twitter.api.v2.model.Tweet;
import lombok.Data;

@Data
public class StreamElement {
  private Tweet data;
  private Includes includes;
  private List<MatchingRules> matching_rules;

  @Data
  public static class MatchingRules {
    private String id;
    private String tag;
  }
}
