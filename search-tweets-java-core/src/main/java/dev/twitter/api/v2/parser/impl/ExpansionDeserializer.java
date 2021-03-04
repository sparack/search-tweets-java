package dev.twitter.api.v2.parser.impl;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import dev.twitter.api.v2.model.Expansion;

public class ExpansionDeserializer extends StdDeserializer<Expansion> {

  public ExpansionDeserializer() {
    this(null);
  }

  public ExpansionDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public Expansion deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
    String value = jp.getCodec().readValue(jp, String.class);
    return Expansion.expansionMap.get(value);
  }
}
