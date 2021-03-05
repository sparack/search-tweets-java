package dev.twitter.api.v2.api;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.Tweet;
import dev.twitter.api.v2.model.rule.Rule;
import dev.twitter.api.v2.model.rule.Rules;
import dev.twitter.api.v2.model.stream.StreamElement;

public interface FilteredStreamAPI {
  Stream<StreamElement> search(String bearerToken, Rules rules, SearchQuery query) throws Exception;
  void createRules(String bearerToken, Rules rules) throws Exception;
  void setupRules(String bearerToken, Rules rules) throws Exception;
  List<Rule> getRules(String bearerToken) throws Exception;
  void deleteRules(String bearerToken, List<Rule> existingRules) throws Exception;
}
