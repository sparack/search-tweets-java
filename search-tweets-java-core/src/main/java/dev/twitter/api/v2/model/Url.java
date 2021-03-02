package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class Url {
  private Integer start;
  private Integer end;
  private String url;
  private String expandedUrl;
  private String displayUrl;
  private String unwoundUrl;
}
