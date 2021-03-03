package dev.twitter.api.v2.model;

import java.util.List;

import lombok.Data;

@Data
public class Entities {
  private List<Annotation> annotations;
  private List<Url> urls;
  private List<Hashtag> hashtags;
  private List<Mention> mentions;
  private List<Cashtag> cashtags;
}
