package dev.twitter.api.v2.api.impl;

import static org.junit.Assert.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.api.SearchAPI;
import dev.twitter.api.v2.exceptions.TwitterException;
import dev.twitter.api.v2.model.SearchType;
import dev.twitter.api.v2.model.token.BearerToken;
import dev.twitter.api.v2.model.token.ConsumerKey;
import dev.twitter.api.v2.model.Expansion;
import dev.twitter.api.v2.model.MediaField;
import dev.twitter.api.v2.model.PlaceField;
import dev.twitter.api.v2.model.PollField;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;
import dev.twitter.api.v2.model.TweetField;
import dev.twitter.api.v2.model.UserField;

@RunWith(JUnit4.class)
public class SearchAPIIntegrationTest {

  @Test
  public void testBasicRecentSearch() throws TwitterException {
    BearerToken bearerToken = new BearerToken();
    bearerToken.setBearerToken("replace-me");

    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setEndTime(ZonedDateTime.ofInstant(Instant.ofEpochMilli(1614586225000L), ZoneId.systemDefault()));
    searchQuery.setExpansions(
        new ArrayList(Arrays.asList(Expansion.AttachmentsPollIds.getParamValue(),
            Expansion.AuthorId.getParamValue(),
            Expansion.ReferencedTweetsId.getParamValue())));
    searchQuery.setMaxResults(12);
    searchQuery.setMediaFields(new ArrayList<>(Arrays.asList(MediaField.duration_ms, MediaField.media_key)));
    searchQuery.setQuery("biden");
    searchQuery.setPlaceFields(new ArrayList<>(Arrays.asList(PlaceField.country_code)));
    searchQuery.setPollFields(new ArrayList<>(Arrays.asList(PollField.id)));
    searchQuery.setUserFields(new ArrayList<>(Arrays.asList(UserField.id)));
    searchQuery.setTweetFields(
        new ArrayList<>(Arrays.asList(TweetField.author_id, TweetField.created_at, TweetField.attachments)));

    SearchAPI searchAPI = new SearchImpl();
    SearchResponse response = searchAPI.search(searchQuery, SearchType.RECENT, bearerToken);
    assertEquals(12, response.getData().size());
    assertFalse(StringUtils.isEmpty(response.getData().get(0).getAuthorId()));
    assertNotNull(response.getMeta());
    assertNotNull(response.getIncludes());
  }

  @Test
  public void testBasicFullArchiveSearch() throws TwitterException {
    BearerToken bearerToken = new BearerToken();
    bearerToken.setBearerToken("replace-me");

    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setEndTime(ZonedDateTime.ofInstant(Instant.ofEpochMilli(1614586225000L), ZoneId.systemDefault()));
    searchQuery.setExpansions(
        new ArrayList(Arrays.asList(Expansion.AttachmentsPollIds.getParamValue(),
            Expansion.AuthorId.getParamValue(),
            Expansion.ReferencedTweetsId.getParamValue())));
    searchQuery.setMaxResults(12);
    searchQuery.setMediaFields(new ArrayList<>(Arrays.asList(MediaField.duration_ms, MediaField.media_key)));
    searchQuery.setQuery("biden");
    searchQuery.setPlaceFields(new ArrayList<>(Arrays.asList(PlaceField.country_code)));
    searchQuery.setPollFields(new ArrayList<>(Arrays.asList(PollField.id)));
    searchQuery.setUserFields(new ArrayList<>(Arrays.asList(UserField.id)));
    searchQuery.setTweetFields(
        new ArrayList<>(Arrays.asList(TweetField.author_id, TweetField.created_at, TweetField.attachments)));

    SearchAPI searchAPI = new SearchImpl();
    SearchResponse response = searchAPI.search(searchQuery, SearchType.FULL_ARCHIVE, bearerToken);
    assertEquals(12, response.getData().size());
    assertFalse(StringUtils.isEmpty(response.getData().get(0).getAuthorId()));
    assertNotNull(response.getMeta());
    assertNotNull(response.getIncludes());
  }

  @Test
  public void testBasicRecentSearchWithConsumerKey() throws TwitterException {
    ConsumerKey consumerKey = new ConsumerKey();
    consumerKey.setApiKey("replace-me");
    consumerKey.setApiSecretKey("replace-me");

    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setExpansions(
        new ArrayList(Arrays.asList(Expansion.AuthorId.getParamValue())));
    searchQuery.setQuery("biden");
    searchQuery.setUserFields(new ArrayList<>(Arrays.asList(UserField.id)));
    searchQuery.setTweetFields(
        new ArrayList<>(Arrays.asList(TweetField.author_id, TweetField.created_at, TweetField.context_annotations)));

    SearchAPI searchAPI = new SearchImpl();
    SearchResponse response = searchAPI.search(searchQuery, SearchType.RECENT, consumerKey);
    assertEquals(10, response.getData().size());
    assertFalse(StringUtils.isEmpty(response.getData().get(0).getAuthorId()));
    assertNotNull(response.getMeta());
    assertNotNull(response.getIncludes());
  }

  @Test(expected = TwitterException.class)
  public void testFailedRequest() throws TwitterException {
    BearerToken bearerToken = new BearerToken();
    bearerToken.setBearerToken("replace-me");

    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setQuery("biden");

    //maxResults not b/w 10 and 100 should throw exception
    searchQuery.setMaxResults(4);
    SearchAPI searchAPI = new SearchImpl();
    searchAPI.search(searchQuery, SearchType.RECENT, bearerToken);
  }

  @Test
  public void testNextToken() throws TwitterException {
    BearerToken bearerToken = new BearerToken();
    bearerToken.setBearerToken("replace-me");

    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setQuery("climate");
    searchQuery.setMaxResults(11);

    SearchAPI searchAPI = new SearchImpl();
    SearchResponse firstResponse = searchAPI.search(searchQuery, SearchType.RECENT, bearerToken);

    searchQuery.setNextToken(firstResponse.getMeta().getNextToken());
    SearchResponse secondResponse = searchAPI.search(searchQuery, SearchType.RECENT, bearerToken);
    assertEquals(11, secondResponse.getData().size());
  }

  @Test
  public void testNoTweets() throws TwitterException {
    BearerToken bearerToken = new BearerToken();
    bearerToken.setBearerToken("replace-me");

    SearchQuery searchQuery = new SearchQuery();
    searchQuery.setQuery("climate");
    searchQuery.setMaxResults(11);
    searchQuery.setUntilId("1");

    SearchAPI searchAPI = new SearchImpl();
    SearchResponse response = searchAPI.search(searchQuery, SearchType.RECENT, bearerToken);
    assertNotNull(response.getMeta());
    assertNull(response.getData());

  }
}
