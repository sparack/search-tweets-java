package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class MediaOrganicMetrics {
  private int playback0_count;
  private int playback100Count;
  private int playback25Count;
  private int playback50Count;
  private int playback75Count;
  private int viewCount;
}
