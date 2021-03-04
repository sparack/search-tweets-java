package dev.twitter.api.v2.model;

import java.util.List;
import lombok.Data;


//TODO: This class can only handle Search api errors response for now
@Data
public class TwitterError {
  private String type;
  private String title;
  private String detail;
  private List<Error> errors;

  @Data
  public static class Error{
    private String message;
    private Parameter parameters;
  }

  @Data
  public static class Parameter {
    private List<String> expansions;
    private List<String> maxResults;
    private List<String> endTime;
    private List<String> mediaFields;
    private List<String> nextToken;
    private List<String> placeFields;
    private List<String> pollFields;
    private List<String> query;
    private List<String> sinceId;
    private List<String> startTime;
    private List<String> tweetFields;
    private List<String> untilId;
    private List<String> userFields;
  }
}
