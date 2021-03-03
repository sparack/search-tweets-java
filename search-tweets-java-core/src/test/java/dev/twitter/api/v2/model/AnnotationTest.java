package dev.twitter.api.v2.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import dev.twitter.api.v2.parser.Parser;
import dev.twitter.api.v2.parser.impl.JacksonParser;
import dev.twitter.api.v2.testutil.FileUtils;

@RunWith(JUnit4.class)
public class AnnotationTest {

  private final Parser parser = new JacksonParser();

  @Test
  public void testReadJson() throws Exception {
    String json = FileUtils.getContents("annotation.json");
    Annotation a = parser.jsonToObject(json, Annotation.class);
    assertEquals(Double.valueOf(0.626), a.getProbability());
    assertEquals("Twitter", a.getNormalizedText());
  }
}
