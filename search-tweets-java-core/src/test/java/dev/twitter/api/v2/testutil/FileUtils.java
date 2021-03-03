package dev.twitter.api.v2.testutil;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.google.common.io.Resources;

public final class FileUtils {

  public static String getContents(String resourceName) throws IOException {
    URL url = Resources.getResource(resourceName);
    return Resources.toString(url, StandardCharsets.UTF_8);
  }
}
