package dev.twitter.api.v2.parser.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.SearchAPI;
import dev.twitter.api.v2.impl.SearchImpl;
import dev.twitter.api.v2.model.Expansion;
import dev.twitter.api.v2.model.MediaField;
import dev.twitter.api.v2.model.PlaceField;
import dev.twitter.api.v2.model.PollField;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;
import dev.twitter.api.v2.model.TweetField;
import dev.twitter.api.v2.model.UserField;

@RunWith(JUnit4.class)
public class SearchAPITest {

  @Test
  public void testBasicSearch() throws IOException, URISyntaxException {
    SearchQuery searchQuery = new SearchQuery();
    //searchQuery.setEndTime(ZonedDateTime.now());
    searchQuery.setExpansions(new ArrayList<>(Arrays.asList(Expansion.AttachmentsPollIds, Expansion.AuthorId)));
    searchQuery.setMaxResults(12);
    searchQuery.setMediaFields(new ArrayList<>(Arrays.asList(MediaField.duration_ms, MediaField.media_key)));
    searchQuery.setQuery("biden");
    searchQuery.setPlaceFields(new ArrayList<>(Arrays.asList(PlaceField.country_code)));
    searchQuery.setPollFields(new ArrayList<>(Arrays.asList(PollField.id)));
    searchQuery.setUserFields(new ArrayList<>(Arrays.asList(UserField.id)));
    List<TweetField> tweetFields =
        new ArrayList<>(Arrays.asList(TweetField.author_id, TweetField.created_at, TweetField.attachments));
    searchQuery.setTweetFields(tweetFields);

    SearchAPI searchAPI = new SearchImpl();
    SearchResponse response = searchAPI.search(searchQuery);
    //TODO: do not print on console
    System.out.println("Response ==> "+ response);
  }
}
