package dev.twitter.api.v2.api;

import dev.twitter.api.v2.exceptions.TwitterException;
import dev.twitter.api.v2.model.ConsumerKey;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;

public interface SearchAPI {
  public SearchResponse search(SearchQuery searchQuery) throws TwitterException;
  public SearchResponse search(SearchQuery searchQuery, ConsumerKey consumerKey) throws TwitterException;
}
