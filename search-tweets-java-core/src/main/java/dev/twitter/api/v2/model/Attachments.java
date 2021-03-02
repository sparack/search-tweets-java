package dev.twitter.api.v2.model;

import java.util.List;

public class Attachments {
  private List<String> pollIds;
  private List<String> mediaKeys;

  public List<String> getPollIds() {
    return pollIds;
  }

  public void setPollIds(List<String> pollIds) {
    this.pollIds = pollIds;
  }

  public List<String> getMediaKeys() {
    return mediaKeys;
  }

  public void setMediaKeys(List<String> mediaKeys) {
    this.mediaKeys = mediaKeys;
  }

  @Override
  public String toString() {
    return "Attachment{" +
        "pollIds=" + pollIds +
        ", mediaKeys=" + mediaKeys +
        '}';
  }
}
