package dev.twitter.api.v2.model;

import java.util.List;

import lombok.Data;

@Data
public class Coordinates {
  private String type;
  private List<Object> coordinates;
}


