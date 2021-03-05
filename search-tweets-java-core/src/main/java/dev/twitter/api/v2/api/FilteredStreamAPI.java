package dev.twitter.api.v2.api;

import java.util.function.Consumer;

import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.Tweet;
import dev.twitter.api.v2.model.rule.Rules;

public interface FilteredStreamAPI {
  void search(String bearerToken, Rules rules, SearchQuery query, Consumer<Tweet> handler) throws Exception;
}
