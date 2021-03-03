package dev.twitter.api.v2.model;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.Data;

@Data
public class SearchQuery {
  private ZonedDateTime endTime;
  private List<Expansion> expansions;
  private Integer maxResults;
  private List<MediaField> mediaFields;
  private String nextToken;
  private List<PlaceField> placeFields;
  private List<PollField> pollFields;
  private String query;
  private String sinceId;
  private ZonedDateTime startTime;
  private List<TweetField> tweetFields;
  private String untilId;
  private List<UserField> userFields;
}
