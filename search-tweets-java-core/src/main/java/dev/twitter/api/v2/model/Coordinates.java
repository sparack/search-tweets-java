package dev.twitter.api.v2.model;

import java.util.List;

public class Coordinates {
  private String type;
  private List<Object> coordinates;

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Object> getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(List<Object> coordinates) {
    this.coordinates = coordinates;
  }

  @Override
  public String toString() {
    return "Coordinates{" +
        "type='" + type + '\'' +
        ", coordinates=" + coordinates +
        '}';
  }
}


