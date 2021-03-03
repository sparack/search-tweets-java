package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class Mention {
  private Integer start;
  private Integer end;
  private String username;
}