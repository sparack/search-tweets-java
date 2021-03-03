package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class TweetNonPublicMetrics {
  private int impressionCount;
  private int urlLinkClicks;
  private int userProfileClicks;
}
