package dev.twitter.api.v2.model;

import java.time.LocalDateTime;
import java.util.List;

public class SearchQuery {
  private LocalDateTime endTime;
  private List<Expansion> expansions;
  private Integer maxResults;
  private List<MediaField> mediaFields;
  private String nextToken;
  private List<PlaceField> placeFields;
  private List<PollField> pollFields;
  private String query;
  private String sinceId;
  private LocalDateTime startTime;
  private List<TweetField> tweetFields;
  private String untilId;
  private List<UserField> userFields;

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public List<Expansion> getExpansions() {
    return expansions;
  }

  public void setExpansions(List<Expansion> expansions) {
    this.expansions = expansions;
  }

  public Integer getMaxResults() {
    return maxResults;
  }

  public void setMaxResults(Integer maxResults) {
    this.maxResults = maxResults;
  }

  public List<MediaField> getMediaFields() {
    return mediaFields;
  }

  public void setMediaFields(List<MediaField> mediaFields) {
    this.mediaFields = mediaFields;
  }

  public String getNextToken() {
    return nextToken;
  }

  public void setNextToken(String nextToken) {
    this.nextToken = nextToken;
  }

  public List<PlaceField> getPlaceFields() {
    return placeFields;
  }

  public void setPlaceFields(List<PlaceField> placeFields) {
    this.placeFields = placeFields;
  }

  public List<PollField> getPollFields() {
    return pollFields;
  }

  public void setPollFields(List<PollField> pollFields) {
    this.pollFields = pollFields;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(String query) {
    this.query = query;
  }

  public String getSinceId() {
    return sinceId;
  }

  public void setSinceId(String sinceId) {
    this.sinceId = sinceId;
  }

  public LocalDateTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }

  public List<TweetField> getTweetFields() {
    return tweetFields;
  }

  public void setTweetFields(List<TweetField> tweetFields) {
    this.tweetFields = tweetFields;
  }

  public String getUntilId() {
    return untilId;
  }

  public void setUntilId(String untilId) {
    this.untilId = untilId;
  }

  public List<UserField> getUserFields() {
    return userFields;
  }

  public void setUserFields(List<UserField> userFields) {
    this.userFields = userFields;
  }

  @Override
  public String toString() {
    return "SearchQuery{" +
        "endTime=" + endTime +
        ", expansions=" + expansions +
        ", maxResults=" + maxResults +
        ", mediaFields=" + mediaFields +
        ", nextToken='" + nextToken + '\'' +
        ", placeFields=" + placeFields +
        ", pollFields=" + pollFields +
        ", query='" + query + '\'' +
        ", sinceId='" + sinceId + '\'' +
        ", startTime=" + startTime +
        ", tweetFields=" + tweetFields +
        ", untilId='" + untilId + '\'' +
        ", userFields=" + userFields +
        '}';
  }
}
