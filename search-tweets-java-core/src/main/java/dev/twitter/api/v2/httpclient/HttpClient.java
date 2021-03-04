package dev.twitter.api.v2.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import dev.twitter.api.v2.exceptions.TwitterException;
import dev.twitter.api.v2.model.TwitterError;
import dev.twitter.api.v2.parser.impl.JacksonParser;

public class HttpClient {

  public static String executeGet(ArrayList<NameValuePair> queryParameters, String bearerToken) throws TwitterException {
    CloseableHttpClient httpClient = HttpClients.custom()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD).build())
        .build();

    URIBuilder uriBuilder = null;
    try {
      uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/search/recent");
      uriBuilder.addParameters(queryParameters);

      HttpGet httpGet = new HttpGet(uriBuilder.build());
      httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
      httpGet.setHeader("Content-Type", "application/json");

      HttpResponse response = httpClient.execute(httpGet);
      HttpEntity entity = response.getEntity();
      String apiResponse = null;
      if (null != entity) {
        apiResponse = EntityUtils.toString(entity, "UTF-8");
      }

      if(response.getStatusLine().getStatusCode() == 200) {
        return apiResponse;
      } else {
        JacksonParser parser = new JacksonParser();
        TwitterError twError = parser.jsonToObject(apiResponse, TwitterError.class);
        throw new TwitterException("Exception occurred while fetching results from Twitter API", response.getStatusLine().getStatusCode(), twError);
      }

    } catch (URISyntaxException | IOException e) {
      throw new TwitterException(e.getMessage(), e);
    }
  }

  //TODO: For filtered stream
  public static HttpResponse executePost(String bearerToken) {
    return null;
  }
}
