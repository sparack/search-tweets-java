package dev.twitter.api.v2.model;

public enum SearchType {
  //TODO figure out a better way to implement

  RECENT("https://api.twitter.com/2/tweets/search/recent"),
  FULL_ARCHIVE("https://api.twitter.com/2/tweets/search/all");

  private final String path;

  SearchType(String path) {
    this.path = path;
  }

  SearchType() {
    this.path = this.name();
  }

  // Use paramvalue to set `expansions` in the SearchQuery
  public String getPath() {
    return this.path;
  }

}
