package dev.twitter.api.v2.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Includes {
  private List<Tweet> tweets;
  private List<User> users;
  private List<Place> places;
  private List<Media> media;
  private List<Poll> polls;

  private Map<String,Object> errors;
}
