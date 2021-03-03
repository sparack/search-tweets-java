package dev.twitter.api.v2.parser.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.SearchInterface;
import dev.twitter.api.v2.impl.SearchImpl;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;
import dev.twitter.api.v2.model.TweetField;

@RunWith(JUnit4.class)
public class SearchAPITest {

  @Test
  public void testBasicSearch() throws IOException, URISyntaxException {
    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setQuery("biden");
    List<TweetField> tweetFields =
        new ArrayList<>(Arrays.asList(TweetField.author_id, TweetField.created_at, TweetField.attachments));
    searchQuery.setTweetFields(tweetFields);

    SearchInterface searchInterface = new SearchImpl();
    SearchResponse response = searchInterface.search(searchQuery);
    System.out.println("Response ==> "+ response);
  }
}
