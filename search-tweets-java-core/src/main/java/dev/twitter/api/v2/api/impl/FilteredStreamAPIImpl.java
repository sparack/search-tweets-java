package dev.twitter.api.v2.api.impl;

import java.text.MessageFormat;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok2c.hc5.json.http.JsonResponseConsumers;

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

import dev.twitter.api.v2.api.FilteredStreamAPI;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.Tweet;
import dev.twitter.api.v2.model.rule.Add;
import dev.twitter.api.v2.model.rule.Rules;
import dev.twitter.api.v2.parser.Parser;
import dev.twitter.api.v2.parser.impl.JacksonParser;

public class FilteredStreamAPIImpl implements FilteredStreamAPI {

  private CloseableHttpClient httpClient = HttpClients.createDefault();

  private CloseableHttpAsyncClient asyncClient = HttpAsyncClients.createSystem();

  private final Parser parser = new JacksonParser();

  @Override
  // using the JSON stream processing as shown in https://ok2c.github.io/httpcomponents-jackson/
  public void search(Rules rules, SearchQuery query, Consumer<Tweet> handler) throws Exception {

    //setFilterRules(rules);

    ObjectMapper objectMapper = ((JacksonParser) parser).getMapper();

    Future<?> future = asyncClient.execute(
        AsyncRequestBuilder.get("uri").build(),
        JsonResponseConsumers.create(objectMapper, Tweet.class),
        new FutureCallback<Message<HttpResponse, Tweet>>() {

          @Override
          public void completed(Message<HttpResponse, Tweet> message) {
            System.out.println("Response status: " + message.getHead().getCode());
            System.out.println(message.getBody());
          }

          @Override
          public void failed(Exception ex) {
            ex.printStackTrace(System.out);
          }

          @Override
          public void cancelled() {
            System.out.println("Cancelled!!!!");
          }

        });
    //future.get();
  }

  private void setFilterRules(Rules rules) throws Exception {
    HttpPost httpPost = new HttpPost("https://api.twitter.com/2/tweets/search/stream/rules");
    httpPost.setEntity(new StringEntity(
        parser.objectToJson(rules),
        ContentType.APPLICATION_JSON
    ));
    // TODO - hardcoded token, replace with real
    String token = "AAAAAAAAAAAAAAAAAAAAANbyNAEAAAAAXjM1DYEPjL8w9GIdqjfr3jXHt74%3Dt40ygjjjegXIqNZgh3yHbW91hGR1L1kXsCWvaolY7isX7j5ajM";
    String headerVal = MessageFormat.format("Bearer {0}", token);
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
}
