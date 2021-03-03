package dev.twitter.api.v2.model;

import java.time.ZonedDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class User {
  private String id;
  private String name;
  private String username;
  private ZonedDateTime createdAt;
  private String description;
  //TODO
  private Map<String,Object> entities;
  private String location;
  private String pinnedTweetId;
  private String profileImageUrl;
  private boolean isProtected;

  private UserPublicMetrics publicMetrics;
  private String url;
  private boolean verified;
  private Map<String,Object> witheld;
}
