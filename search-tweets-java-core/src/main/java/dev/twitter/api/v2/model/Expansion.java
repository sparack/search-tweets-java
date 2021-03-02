package dev.twitter.api.v2.model;

public enum Expansion {

  // TODO - think of a better way to organize these
  AttachmentsPollIds("attachments.poll_ids"),
  AttachmentsMediaKeys("attachments.media_keys"),
  AuthorId("author_id"),
  EntitiesMentionsUsername("entities.mentions.username"),
  GeoPlaceId("geo.place_id"),
  InReplyToUserId("in_reply_to_user_id"),
  ReferencedTweetsId("referenced_tweets.id"),
  ReferencedTweetsAuthorId("referenced_tweets.id.author_id");

  private final String paramValue;

  Expansion(String paramValue) {
    this.paramValue = paramValue;
  }

  Expansion() {
    this.paramValue = this.name();
  }
}
