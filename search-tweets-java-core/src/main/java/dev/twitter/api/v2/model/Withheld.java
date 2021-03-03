package dev.twitter.api.v2.model;

import java.util.List;

import lombok.Data;

@Data
public class Withheld {
  enum Scope {tweet, user}

  private Boolean copyright;
  private List<String> countryCodes;
  private Scope scope;

}
