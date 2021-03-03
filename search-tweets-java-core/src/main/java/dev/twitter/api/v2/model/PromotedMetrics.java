package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class PromotedMetrics {
  private int impressionCount;
  private int likeCount;
  private int replyCount;
  private int retweetCount;
  private int urlLinkClicks;
  private int userProfileClicks;
}
