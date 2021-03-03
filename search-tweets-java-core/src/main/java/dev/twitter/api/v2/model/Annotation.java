package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class Annotation {
  private Integer start;
  private Integer end;
  private Double probability;
  private String type;
  private String normalizedText;
}
