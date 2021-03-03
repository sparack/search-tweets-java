package dev.twitter.api.v2.parser.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.twitter.api.v2.parser.Parser;

public class JacksonParser implements Parser {

  private ObjectMapper mapper = new ObjectMapper();

  @Override
  public <T> T jsonToObject(String json, Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public <T> String objectToJson(T object) {
    try {
      return mapper.writeValueAsString(object);
    }
    catch (RuntimeException e) {
      throw e;
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
