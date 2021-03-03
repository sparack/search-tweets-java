package dev.twitter.api.v2.model;

import lombok.Data;

@Data
public class Media {

  enum Type {
    animated_gif, photo, video
  }
  private String mediaKey;
  private Type type;
  private int durationMs;
  private int height;

  private MediaNonPublicMetrics nonPublicMetrics;
  private MediaOrganicMetrics organicMetrics;

  private String previewImageUrl;

  private MediaPromotedMetrics promotedMetrics;
  private MediaPublicMetrics publicMetrics;

  private int width;
}
