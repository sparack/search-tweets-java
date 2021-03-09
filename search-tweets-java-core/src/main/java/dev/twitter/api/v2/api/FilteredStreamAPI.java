package dev.twitter.api.v2.api;

import java.util.List;
import java.util.stream.Stream;

import dev.twitter.api.v2.model.FilteredStreamQuery;
import dev.twitter.api.v2.model.rule.Rule;
import dev.twitter.api.v2.model.rule.Rules;
import dev.twitter.api.v2.model.stream.StreamElement;
import dev.twitter.api.v2.model.token.BearerToken;

public interface FilteredStreamAPI {
  Stream<StreamElement> search(FilteredStreamQuery query, Rules rules, BearerToken bearerToken) throws Exception;
  void createRules(Rules rules, BearerToken bearerToken) throws Exception;
  void setupRules(Rules rules, BearerToken bearerToken) throws Exception;
  List<Rule> getRules(BearerToken bearerToken) throws Exception;
  void deleteRules(List<Rule> existingRules, BearerToken bearerToken) throws Exception;
}
