package dev.twitter.api.v2.model;

import java.util.List;

import lombok.Data;

@Data
public class FilteredStreamQuery {
  private List<Expansion> expansions;
  private List<MediaField> mediaFields;
  private List<PlaceField> placeFields;
  private List<PollField> pollFields;
  private List<TweetField> tweetFields;
  private List<UserField> userFields;

  public static enum TweetField {
    attachments,
    author_id,
    context_annotations,
    conversation_id,
    created_at,
    entities, geo,
    id,
    in_reply_to_user_id,
    lang,
    public_metrics,
    possibly_sensitive,
    referenced_tweets,
    reply_settings,
    source,
    text,
    withheld
  }

  public static enum MediaField {
    duration_ms,
    height,
    media_key,
    preview_image_url,
    type,
    url,
    width,
    public_metrics
  }
}
