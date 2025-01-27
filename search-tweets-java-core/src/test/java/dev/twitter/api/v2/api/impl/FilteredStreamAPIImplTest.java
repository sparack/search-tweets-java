package dev.twitter.api.v2.api.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.api.FilteredStreamAPI;
import dev.twitter.api.v2.model.Expansion;
import dev.twitter.api.v2.model.FilteredStreamQuery;
import dev.twitter.api.v2.model.MediaField;
import dev.twitter.api.v2.model.PlaceField;
import dev.twitter.api.v2.model.PollField;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.TweetField;
import dev.twitter.api.v2.model.UserField;
import dev.twitter.api.v2.model.rule.Add;
import dev.twitter.api.v2.model.rule.Rules;
import dev.twitter.api.v2.model.stream.StreamElement;
import dev.twitter.api.v2.model.token.BearerToken;

@RunWith(JUnit4.class)
public class FilteredStreamAPIImplTest {

  private FilteredStreamAPI api = new FilteredStreamAPIImpl();

  @Test
  public void testResponse() throws Exception {
    CountDownLatch latch = new CountDownLatch(1);
    Rules rules = new Rules();
    Add add = new Add();
    add.setTag("cats with media");
    add.setValue("cat has:media");
    rules.setAdd(Collections.singletonList(add));

    FilteredStreamQuery filteredStreamQuery = new FilteredStreamQuery();
    filteredStreamQuery.setExpansions(new ArrayList(Arrays.asList(Expansion.AuthorId.getParamValue())));
    filteredStreamQuery.setMediaFields(new ArrayList<>(Arrays.asList(FilteredStreamQuery.MediaField.media_key)));
    filteredStreamQuery.setPlaceFields(new ArrayList<>(Arrays.asList(PlaceField.country_code)));
    filteredStreamQuery.setPollFields(new ArrayList<>(Arrays.asList(PollField.id)));
    filteredStreamQuery.setUserFields(new ArrayList<>(Arrays.asList(UserField.id)));
    filteredStreamQuery.setTweetFields(new ArrayList<>(Arrays.asList(
        FilteredStreamQuery.TweetField.author_id,
        FilteredStreamQuery.TweetField.created_at)));

    BearerToken bearerToken = new BearerToken();
    bearerToken.setBearerToken("replace-me");
    Stream<StreamElement> stream = api.search(filteredStreamQuery, rules, bearerToken);

    stream.limit(5).forEach(System.out::println);
    System.out.println("dne");
  }
}
