package dev.twitter.api.v2.model.rule;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class RuleResponse {

  @Data
  public static class Meta {
    private ZonedDateTime sent;
  }

  private List<Rule> data;
  private Meta meta;
  private Map<String,Object> errors;

}
