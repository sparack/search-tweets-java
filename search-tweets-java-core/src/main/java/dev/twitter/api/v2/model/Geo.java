package dev.twitter.api.v2.model;

public class Geo {
  private Coordinates coordinates;
  private String placeId;

  public Coordinates getCoordinates() {
    return coordinates;
  }

  public void setCoordinates(Coordinates coordinates) {
    this.coordinates = coordinates;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  @Override
  public String toString() {
    return "Geo{" +
        "coordinates=" + coordinates +
        ", placeId='" + placeId + '\'' +
        '}';
  }
}
