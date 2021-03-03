package dev.twitter.api.v2.model;

import java.util.List;

import lombok.Data;

@Data
public class Url {
  private Integer start;
  private Integer end;
  private String url;
  private String expandedUrl;
  private String displayUrl;
  private String unwoundUrl;

  private List<Image> images;

  private int status;
  private String title;
  private String description;
}
