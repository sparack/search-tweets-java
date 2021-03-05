package dev.twitter.api.v2.impl;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import dev.twitter.api.v2.SearchAPI;
import dev.twitter.api.v2.exceptions.TwitterException;
import dev.twitter.api.v2.httpclient.HttpClient;
import dev.twitter.api.v2.model.ConsumerKey;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;
import dev.twitter.api.v2.parser.impl.JacksonParser;
import dev.twitter.api.v2.util.AuthUtil;
import dev.twitter.api.v2.util.DateTimeUtil;

public class SearchImpl implements SearchAPI {
  JacksonParser parser = new JacksonParser();

  @Override
  public SearchResponse search(SearchQuery searchQuery) throws TwitterException {
    ArrayList<NameValuePair> queryParameters = convertSearchQueryToParams(searchQuery);

    String searchResponse =
        HttpClient.executeGet(queryParameters, "hard-coded bearer token");
    return parser.jsonToObject(searchResponse, SearchResponse.class);
  }

  @Override
  public SearchResponse search(SearchQuery searchQuery, ConsumerKey consumerKey) throws TwitterException {
    ArrayList<NameValuePair> queryParameters = convertSearchQueryToParams(searchQuery);

    String searchResponse =
        HttpClient.executeGet(queryParameters, AuthUtil.getBearerTokenFromConsumerKey(consumerKey));
    return parser.jsonToObject(searchResponse, SearchResponse.class);
  }

  /**
   * convert SearchQuery into string like "from:TwitterDev OR from:SnowBotDev OR from:DailyNASA"
   * @param query
   * @return
   */
  private ArrayList<NameValuePair> convertSearchQueryToParams(SearchQuery query) {
    //TODO: Find a better way to do this

    ArrayList<NameValuePair> params = new ArrayList<>();

    if(query.getEndTime() != null)
      params.add(new BasicNameValuePair("end_time", DateTimeUtil.toISO(query.getEndTime())));

    if(query.getExpansions() != null && !query.getExpansions().isEmpty())
      params.add(new BasicNameValuePair("expansions", StringUtils.join(query.getExpansions(), ',')));

    if(query.getMaxResults() != null)
      params.add(new BasicNameValuePair("max_results", query.getMaxResults().toString()));

    if(query.getMediaFields() != null && !query.getMediaFields().isEmpty())
      params.add(new BasicNameValuePair("media.fields", StringUtils.join(query.getMediaFields(), ',')));

    if(!StringUtils.isEmpty(query.getNextToken()))
      params.add(new BasicNameValuePair("next_token", query.getNextToken()));

    if (query.getPlaceFields() != null && !query.getPlaceFields().isEmpty())
      params.add(new BasicNameValuePair("place.fields", StringUtils.join(query.getPlaceFields(), ',')));

    if (query.getPollFields() != null && !query.getPollFields().isEmpty())
      params.add(new BasicNameValuePair("poll.fields", StringUtils.join(query.getPollFields(), ',')));

    if(!StringUtils.isEmpty(query.getQuery()))
      params.add(new BasicNameValuePair("query", query.getQuery()));

    if (!StringUtils.isEmpty(query.getSinceId()))
      params.add(new BasicNameValuePair("since_id", query.getSinceId()));

    if(query.getStartTime() != null)
      params.add(new BasicNameValuePair("start_time", DateTimeUtil.toISO(query.getStartTime())));

    if(query.getTweetFields() != null && !query.getTweetFields().isEmpty())
      params.add(new BasicNameValuePair("tweet.fields", StringUtils.join(query.getTweetFields(), ',')));

    if (!StringUtils.isEmpty(query.getUntilId()))
      params.add(new BasicNameValuePair("until_id", query.getUntilId()));

    if(query.getUserFields() != null && !query.getUserFields().isEmpty())
      params.add(new BasicNameValuePair("user.fields", StringUtils.join(query.getUserFields(), ',')));

    return params;
  }
}
