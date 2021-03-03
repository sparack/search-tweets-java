package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class TweetPublicMetrics {
  private int retweetCount;
  private int replyCount;
  private int likeCount;
  private int quoteCount;
}
