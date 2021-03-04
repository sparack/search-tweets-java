package dev.twitter.api.v2.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonValue;

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

  public static final Map<String, Expansion> expansionMap;
  static {
    expansionMap = Collections.unmodifiableMap(Arrays.stream(Expansion.values())
        .collect(Collectors.toMap(e -> e.paramValue, Function.identity())));
  }

  @JsonValue
  private final String paramValue;

  Expansion(String paramValue) {
    this.paramValue = paramValue;
  }

  Expansion() {
    this.paramValue = this.name();
  }

}
