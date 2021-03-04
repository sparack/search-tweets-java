package dev.twitter.api.v2.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.parser.Parser;
import dev.twitter.api.v2.parser.impl.JacksonParser;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class ExpansionTest {

  private final Parser parser = new JacksonParser();

  @Test
  public void testObjectToJson() {
    Expansion e = Expansion.AttachmentsPollIds;
    assertEquals("\"attachments.poll_ids\"",
        parser.objectToJson(e));
  }

  @Test
  public void testJsonToObject() {
    Expansion e = parser.jsonToObject("\"attachments.media_keys\"", Expansion.class);
    assertEquals(Expansion.AttachmentsMediaKeys, e);
  }
}
