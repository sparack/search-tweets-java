package dev.twitter.api.v2.model;

public enum UserField {

  created_at,
  description,
  entities,
  id,
  location,
  name,
  pinned_tweet_id,
  profile_image_url,
  is_protected("protected"),
  public_metrics,
  url, username,
  verified,
  withheld;

  private String realName;

  UserField() {
    this.realName = this.name();
  }

  UserField(String realName) {
    this.realName = realName;
  }


}
