package dev.twitter.api.v2.model;

public class ReferencedTweet {

  enum ReferenceType {
    retweeted, quoted, replied_to
  }

  private String id;
  private ReferenceType type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ReferenceType getType() {
    return type;
  }

  public void setType(ReferenceType type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "ReferencedTweet{" +
        "id='" + id + '\'' +
        ", type=" + type +
        '}';
  }
}
