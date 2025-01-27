package dev.twitter.api.v2.api;

import dev.twitter.api.v2.exceptions.TwitterException;
import dev.twitter.api.v2.model.SearchType;
import dev.twitter.api.v2.model.token.BearerToken;
import dev.twitter.api.v2.model.token.ConsumerKey;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;

public interface SearchAPI {
  public SearchResponse search(SearchQuery searchQuery, SearchType searchType, BearerToken bearerToken) throws TwitterException;
  public SearchResponse search(SearchQuery searchQuery, SearchType searchType, ConsumerKey consumerKey) throws TwitterException;
}
