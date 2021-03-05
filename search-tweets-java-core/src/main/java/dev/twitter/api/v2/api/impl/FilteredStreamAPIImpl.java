package dev.twitter.api.v2.api.impl;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.net.URIBuilder;

import dev.twitter.api.v2.api.FilteredStreamAPI;
import dev.twitter.api.v2.model.SearchQuery;
import dev.twitter.api.v2.model.rule.Delete;
import dev.twitter.api.v2.model.rule.Rule;
import dev.twitter.api.v2.model.rule.RuleResponse;
import dev.twitter.api.v2.model.rule.Rules;
import dev.twitter.api.v2.model.stream.StreamElement;
import dev.twitter.api.v2.parser.Parser;
import dev.twitter.api.v2.parser.impl.JacksonParser;

public class FilteredStreamAPIImpl implements FilteredStreamAPI {

  private final CloseableHttpClient httpClient = HttpClients.createDefault();

  private final Parser parser = new JacksonParser();

  private final ExecutorService executorService = Executors.newFixedThreadPool(3);

  @Override
  public Stream<StreamElement> search(String bearerToken, Rules rules, SearchQuery query) throws Exception {

    setupRules(bearerToken, rules);

    ObjectMapper mapper = ((JacksonParser) parser).getMapper();

    HttpGet httpGet = new HttpGet("https://api.twitter.com/2/tweets/search/stream");
    httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));

    BlockingQueue<StreamElement> queue = new LinkedBlockingQueue<>(50);
    executorService.submit(() -> {
      try {
        CloseableHttpResponse response = httpClient.execute(httpGet);
        InputStream inputStream = response.getEntity().getContent();
        JsonParser parser = mapper.createParser(inputStream);
        while (!parser.isClosed()) {
          StreamElement element = mapper.readValue(parser, StreamElement.class);
          queue.add(element);
        }
      }
      catch (Exception e) {
        throw new RuntimeException(e);
      }
    });

    // TODO - close the CloseableHttpResponse when the Stream is closed
    return Stream.generate(() -> {
      try {
        return queue.take();
      }
      catch (InterruptedException ie) {
        throw new RuntimeException(ie);
      }
    });
  }

  @Override
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
      String responseStr = EntityUtils.toString(entity);
      // TODO - do we do anything with response?
      System.out.println("createRules response" + responseStr);
    }
  }

  @Override
  public void setupRules(String bearerToken, Rules rules) throws Exception {
    List<Rule> existingRules = getRules(bearerToken);
    if (!existingRules.isEmpty()) {
      deleteRules(bearerToken, existingRules);
    }
    createRules(bearerToken, rules);
  }

  @Override
  public List<Rule> getRules(String bearerToken) throws Exception {
    URIBuilder uriBuilder = new URIBuilder("https://api.twitter.com/2/tweets/search/stream/rules");

    HttpGet httpGet = new HttpGet(uriBuilder.build());
    httpGet.setHeader("Authorization", String.format("Bearer %s", bearerToken));
    httpGet.setHeader("content-type", "application/json");
    CloseableHttpResponse response = httpClient.execute(httpGet);
    HttpEntity entity = response.getEntity();
    String json = EntityUtils.toString(entity, "UTF-8");

    RuleResponse ruleResponse = parser.jsonToObject(json, RuleResponse.class);
    return ruleResponse.getData();
  }

  @Override
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
