package dev.twitter.api.v2.model;

import java.util.List;

import lombok.Data;

@Data
public class Attachments {
  private List<String> pollIds;
  private List<String> mediaKeys;
}
