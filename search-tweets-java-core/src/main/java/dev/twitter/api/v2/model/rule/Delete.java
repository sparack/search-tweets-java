package dev.twitter.api.v2.model.rule;

import java.util.List;

import lombok.Data;

@Data
public class Delete {
  private List<String> ids;
}
