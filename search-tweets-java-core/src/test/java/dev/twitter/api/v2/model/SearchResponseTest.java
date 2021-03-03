package dev.twitter.api.v2.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.parser.Parser;
import dev.twitter.api.v2.parser.impl.JacksonParser;
import dev.twitter.api.v2.testutil.FileUtils;

@RunWith(JUnit4.class)
public class SearchResponseTest {

  private final Parser parser = new JacksonParser();

  @Test
  public void testReadJson() throws Exception {
    String json = FileUtils.getContents("search_response.json");
    SearchResponse s = parser.jsonToObject(json, SearchResponse.class);

    //assertEquals("Twitter", s.);
  }
}
