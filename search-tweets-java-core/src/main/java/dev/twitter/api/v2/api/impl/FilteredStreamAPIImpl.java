package dev.twitter.api.v2.api.impl;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok2c.hc5.json.http.JsonResponseConsumers;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.Message;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.nio.support.AsyncRequestBuilder;
import org.apache.hc.core5.net.URIBuilder;

import dev.twitter.api.v2.api.FilteredStreamAPI;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.Tweet;
import dev.twitter.api.v2.model.rule.Delete;
import dev.twitter.api.v2.model.rule.Rule;
import dev.twitter.api.v2.model.rule.Rules;
import dev.twitter.api.v2.parser.Parser;
import dev.twitter.api.v2.parser.impl.JacksonParser;

public class FilteredStreamAPIImpl implements FilteredStreamAPI {

  private CloseableHttpClient httpClient = HttpClients.createDefault();

  private CloseableHttpAsyncClient asyncClient = HttpAsyncClients.createDefault();

  private final Parser parser = new JacksonParser();

  @Override
  // using the JSON stream processing as shown in https://ok2c.github.io/httpcomponents-jackson/
  public void search(String bearerToken, Rules rules, SearchQuery query, Consumer<Tweet> handler) throws Exception {

    //setRules(rules);

    ObjectMapper objectMapper = ((JacksonParser) parser).getMapper();
    String headerVal = MessageFormat.format("Bearer {0}", bearerToken);

    asyncClient.start();

    Future<?> future = asyncClient.execute(

        AsyncRequestBuilder.get("https://api.twitter.com/2/tweets/search/stream").addHeader("Authorization", headerVal).build(),
        JsonResponseConsumers.create(objectMapper, new TypeReference<Map<String,Object>>() {}),
        new FutureCallback<Message<HttpResponse, Map<String,Object>>>() {

          @Override
          public void completed(Message<HttpResponse, Map<String,Object>> message) {
            System.out.println("Response status: " + message.getHead().getCode());
          }

          @Override
          public void failed(Exception ex) {
            ex.printStackTrace();
          }

          @Override
          public void cancelled() {
            System.out.println("Cancelled!!!!");
          }

        });
    future.get();
  }

  public void createRules(String bearerToken, Rules rules) throws Exception {
    HttpPost httpPost = new HttpPost("https://api.twitter.com/2/tweets/search/stream/rules");
    httpPost.setEntity(new StringEntity(
        parser.objectToJson(rules),
        ContentType.APPLICATION_JSON
    ));
    String headerVal = MessageFormat.format("Bearer {0}", bearerToken);
    httpPost.setHeader("Authorization", headerVal);

    try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
      System.out.println(response.getCode() + " " + response.getReasonPhrase());
      HttpEntity entity = response.getEntity();
      // do something useful with the response body
      // and ensure it is fully consumed
      String responseStr = EntityUtils.toString(entity);
      System.out.println(responseStr);
    }
  }

  public void setupRules(String bearerToken, Rules rules) throws Exception {
    List<Rule> existingRules = getRules(bearerToken);
    if (!existingRules.isEmpty()) {
      deleteRules(bearerToken, existingRules);
    }
    createRules(bearerToken, rules);
  }

  public List<Rule> getRules(String bearerToken) throws Exception {
    URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/search/stream/rules");

    HttpGet httpGet = new HttpGet(uriBuilder.build());
    httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
    httpGet.setHeader("content-type", "application/json");
    CloseableHttpResponse response = httpClient.execute(httpGet);
    HttpEntity entity = response.getEntity();
    String json = EntityUtils.toString(entity, "UTF-8");

    return ((JacksonParser) parser).jsonToObject(json, new TypeReference<List<Rule>>() {});
  }

  public void deleteRules(String bearerToken, List<Rule> existingRules) throws Exception {
    URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/search/stream/rules");

    HttpPost httpPost = new HttpPost(uriBuilder.build());
    httpPost.setHeader("Authorization", String.format("Bearer %s", bearerToken));
    httpPost.setHeader("content-type", "application/json");
    Delete d = new Delete();
    d.setIds(existingRules.stream().map(Rule::getId).collect(Collectors.toList()));
    StringEntity body = new StringEntity(parser.objectToJson(d));
    httpPost.setEntity(body);
    CloseableHttpResponse response = httpClient.execute(httpPost);
    HttpEntity entity = response.getEntity();
    if (null != entity) {
      System.out.println(EntityUtils.toString(entity, "UTF-8"));
    }
  }
}
