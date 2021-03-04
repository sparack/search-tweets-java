package dev.twitter.api.v2.parser.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import dev.twitter.api.v2.parser.Parser;

public class JacksonParser implements Parser {

  private final ObjectMapper mapper;

  public JacksonParser() {
    mapper = new ObjectMapper();
    mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    mapper.registerModule(new JavaTimeModule());
  }

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

  public ObjectMapper getMapper() {
    return mapper;
  }
}
