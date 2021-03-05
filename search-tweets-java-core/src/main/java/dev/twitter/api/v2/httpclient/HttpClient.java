package dev.twitter.api.v2.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.net.URIBuilder;

import dev.twitter.api.v2.exceptions.TwitterException;
import dev.twitter.api.v2.model.TwitterError;
import dev.twitter.api.v2.parser.impl.JacksonParser;

public class HttpClient {

  public static String executeGet(List<NameValuePair> queryParameters, String bearerToken) throws TwitterException {
    CloseableHttpClient httpClient = HttpClients.custom()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setCookieSpec("standard").build())
        .build();

    URIBuilder uriBuilder = null;
    try {
      uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/search/recent");
      uriBuilder.addParameters(queryParameters);

      HttpGet httpGet = new HttpGet(uriBuilder.build());
      httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
      httpGet.setHeader("Content-Type", "application/json");

      CloseableHttpResponse response = httpClient.execute(httpGet);
      HttpEntity entity = response.getEntity();
      String apiResponse = null;
      if (null != entity) {
        apiResponse = EntityUtils.toString(entity, "UTF-8");
      }

      if(response.getCode() == 200) {
        return apiResponse;
      } else {
        JacksonParser parser = new JacksonParser();
        TwitterError twError = parser.jsonToObject(apiResponse, TwitterError.class);
        throw new TwitterException("Exception occurred while fetching results from Twitter API", response.getCode(), twError);
      }

    } catch (URISyntaxException | IOException | ParseException e) {
      throw new TwitterException(e.getMessage(), e);
    }
  }
}
