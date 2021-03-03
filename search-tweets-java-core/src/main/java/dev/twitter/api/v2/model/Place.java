package dev.twitter.api.v2.model;

import java.util.List;

import lombok.Data;

@Data
public class Place {
  private String fullName;
  private String id;
  private List<String> containedWithin;
}
