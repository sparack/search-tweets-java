package dev.twitter.api.v2.exceptions;

import dev.twitter.api.v2.model.TwitterError;


public class TwitterException extends Exception {
  private int statusCode = -1;
  private String errorMessage = null;


  public TwitterException(String message, Throwable cause) {
    super(message, cause);
  }

  public TwitterException(String message, int statusCode,TwitterError twError) {
    super(message, (Throwable) null);
    this.statusCode = statusCode;
    if(twError.getErrors() != null && !twError.getErrors().isEmpty()) {
      this.errorMessage = twError.getErrors().get(0).getMessage();
    }
  }

  @Override
  public String getMessage() {
    if(errorMessage != null && statusCode != -1) {
      return this.errorMessage;
    } else {
      return super.getMessage();
    }
  }
}
