package dev.twitter.api.v2.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import dev.twitter.api.v2.SearchInterface;
import dev.twitter.api.v2.httpclient.HttpClient;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.SearchResponse;
import dev.twitter.api.v2.parser.impl.JacksonParser;

public class SearchImpl implements SearchInterface {
  JacksonParser parser = new JacksonParser();

  @Override
  public SearchResponse search(SearchQuery searchQuery) throws IOException, URISyntaxException {
    //convert SearchQuery into string "from:TwitterDev OR from:SnowBotDev OR from:DailyNASA"
    ArrayList<NameValuePair> queryParameters;
    queryParameters = new ArrayList<>();

    if(!StringUtils.isEmpty(searchQuery.getQuery()))
      queryParameters.add(new BasicNameValuePair("query", searchQuery.getQuery()));
    if(searchQuery.getTweetFields() != null && !searchQuery.getTweetFields().isEmpty())
      queryParameters.add(new BasicNameValuePair("tweet.fields", StringUtils.join(searchQuery.getTweetFields(), ',')));
    //TODO: Manisha - add remaining


    String searchResponse =
        HttpClient.executeGet(queryParameters, "AAAAAAAAAAAAAAAAAAAAAD70NAEAAAAAW%2FVnxAuksoqwrJ%2FNmsYaUcDgalg%3D6XkE1NYMJqTIGZfOqzdn5c6t8qYdCrMLmgslQkgjxx4dlPw6hh");

    return parser.jsonToObject(searchResponse, SearchResponse.class);
  }
}
