package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class ReferencedTweet {

  enum ReferenceType {
    retweeted, quoted, replied_to
  }

  private String id;
  private ReferenceType type;
}
