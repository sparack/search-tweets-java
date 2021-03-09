package dev.twitter.api.v2.model.token;

import lombok.Data;

@Data
public class ConsumerKey {
  private String apiKey;
  private String apiSecretKey;
}
