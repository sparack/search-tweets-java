package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class Meta {
  private String newestId;
  private String oldestId;
  private Integer resultCount;
  private String nextToken;
}
