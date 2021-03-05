package dev.twitter.api.v2.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import dev.twitter.api.v2.exceptions.TwitterException;
import dev.twitter.api.v2.model.token.ConsumerKey;
import dev.twitter.api.v2.model.TwitterError;
import dev.twitter.api.v2.parser.impl.JacksonParser;

public class AuthUtil {

  /*
   * Helper method that generates bearer token by calling the /oauth2/token endpoint
   * */
  public static String getBearerTokenFromConsumerKey(ConsumerKey consumerKey) throws TwitterException {
    String bearerToken = null;

    CloseableHttpClient httpClient = HttpClients.custom()
        .setDefaultRequestConfig(RequestConfig.custom()
            .setCookieSpec(CookieSpecs.STANDARD).build())
        .build();

    URIBuilder uriBuilder = null;
    try {
      uriBuilder = new URIBuilder("https://api.twitter.com/oauth2/token");
      ArrayList<NameValuePair> postParameters;
      postParameters = new ArrayList<>();
      postParameters.add(new BasicNameValuePair("grant_type", "client_credentials"));
      uriBuilder.addParameters(postParameters);

      HttpPost httpPost = new HttpPost(uriBuilder.build());
      httpPost.setHeader("Authorization", String.format("Basic %s", getBase64EncodedString(consumerKey)));
      httpPost.setHeader("Content-Type", "application/json");

      HttpResponse response = httpClient.execute(httpPost);
      HttpEntity entity = response.getEntity();

      if (response.getStatusLine().getStatusCode() == 200 && null != entity) {
        try (InputStream inputStream = entity.getContent()) {
          ObjectMapper mapper = new ObjectMapper();
          Map<String, Object> jsonMap = mapper.readValue(inputStream, Map.class);
          bearerToken = jsonMap.get("access_token").toString();
          return bearerToken;
        }
      } else {
        JacksonParser parser = new JacksonParser();
        TwitterError twError = parser.jsonToObject(EntityUtils.toString(entity, "UTF-8"), TwitterError.class);
        throw new TwitterException("Exception occurred while fetching results from Twitter API", response.getStatusLine().getStatusCode(), twError);
      }
    } catch (URISyntaxException | IOException e) {
      throw new TwitterException(e.getMessage(), e);
    }
  }

  /*
   * Helper method that generates the Base64 encoded string
   *
   * */
  private static String getBase64EncodedString(ConsumerKey consumerKey) {
    String s = String.format("%s:%s", consumerKey.getApiKey(), consumerKey.getApiSecretKey());
    return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
  }

}
