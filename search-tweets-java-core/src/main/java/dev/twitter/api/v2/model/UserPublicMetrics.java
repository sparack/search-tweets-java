package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class UserPublicMetrics {
  private int followersCount;
  private int followingCount;
  private int tweetCount;
  private int listedCount;
}
