package dev.twitter.api.v2.model.stream;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class StreamElement {
  private Map<String,String> data;
  private List<Map<String,String>> matching_rules;
}
