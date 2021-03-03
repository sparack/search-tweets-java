package dev.twitter.api.v2.parser;

public interface Parser {
  <T> T jsonToObject(String json, Class<T> clazz);
  <T> String objectToJson(T object);
}
