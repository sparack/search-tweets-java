package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class Geo {
  private Coordinates coordinates;
  private String placeId;
}
