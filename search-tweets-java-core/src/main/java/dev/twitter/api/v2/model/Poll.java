package dev.twitter.api.v2.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Poll {
  private String id;
  private List<Map<String,Object>> options;
}
