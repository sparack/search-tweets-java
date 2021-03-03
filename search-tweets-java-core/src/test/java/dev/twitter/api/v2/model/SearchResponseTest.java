package dev.twitter.api.v2.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.parser.Parser;
import dev.twitter.api.v2.parser.impl.JacksonParser;
import dev.twitter.api.v2.testutil.FileUtils;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class SearchResponseTest {

  private final Parser parser = new JacksonParser();

  @Test
  public void testReadJson() throws Exception {
    String json = FileUtils.getContents("search_response.json");
    SearchResponse s = parser.jsonToObject(json, SearchResponse.class);

    // TODO - better tests
    assertEquals(1, s.getData().size());
    Tweet t =  s.getData().get(0);
    assertEquals("We believe the best future version of our API will come from building it with YOU. Here’s to another great year with everyone who builds on the Twitter platform. We can’t wait to continue working with you in the new year. https://t.co/yvxdK6aOo2",t.getText());

    assertEquals(1, t.getReferencedTweets().size());
    assertEquals("1212092627178287104", t.getReferencedTweets().get(0).getId());
  }

  @Test
  public void testReadJsonWithPoll() throws Exception {
    String json = FileUtils.getContents("search-response-with-poll.json");
    SearchResponse s = parser.jsonToObject(json, SearchResponse.class);

    Poll p = s.getIncludes().getPolls().get(0);
    assertEquals(1440, p.getDurationMinutes());
  }
}
