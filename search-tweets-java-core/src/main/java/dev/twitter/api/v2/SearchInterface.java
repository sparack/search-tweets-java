package dev.twitter.api.v2;

import java.io.IOException;
import java.net.URISyntaxException;

import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;

public interface SearchInterface {
  public SearchResponse search(SearchQuery searchQuery) throws IOException, URISyntaxException;
}
