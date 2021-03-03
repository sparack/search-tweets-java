package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class MediaNonPublicMetrics {
  private int playback0Count;
  private int playback100Count;
  private int playback25Count;
  private int playback50Count;
  private int playback75Count;
}
