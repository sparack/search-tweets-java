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
import dev.twitter.api.v2.model.MediaField;
import dev.twitter.api.v2.model.PlaceField;
import dev.twitter.api.v2.model.PollField;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.TweetField;
import dev.twitter.api.v2.model.UserField;
import dev.twitter.api.v2.model.rule.Add;
import dev.twitter.api.v2.model.rule.Rules;
import dev.twitter.api.v2.model.stream.StreamElement;

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

    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setExpansions(
        new ArrayList(Arrays.asList(Expansion.AttachmentsPollIds.getParamValue(),
            Expansion.AuthorId.getParamValue(),
            Expansion.ReferencedTweetsId.getParamValue())));
    searchQuery.setMediaFields(new ArrayList<>(Arrays.asList(MediaField.duration_ms, MediaField.media_key)));
    searchQuery.setPlaceFields(new ArrayList<>(Arrays.asList(PlaceField.country_code)));
    searchQuery.setPollFields(new ArrayList<>(Arrays.asList(PollField.id)));
    searchQuery.setUserFields(new ArrayList<>(Arrays.asList(UserField.id)));
    searchQuery.setTweetFields(
        new ArrayList<>(Arrays.asList(TweetField.author_id, TweetField.created_at, TweetField.attachments)));

    String bearerToken = System.getenv("BEARER_TOKEN");
    Stream<StreamElement> stream = api.search(bearerToken, rules, null);

    stream.limit(5).forEach(System.out::println);
    System.out.println("dne");
  }
}
