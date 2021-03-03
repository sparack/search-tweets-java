package dev.twitter.api.v2.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class Tweet {
  private String id;
  private String text;
  private LocalDateTime createdAt;
  private String authorId;
  private String conversationId;
  private String inReplyToUserId;
  private List<ReferencedTweet> referencedTweets;
  private Attachments attachments;
  private Geo geo;
  private List<ContextAnnotations> contextAnnotations;
  private Entities entities;
  private Withheld withheld;

}
