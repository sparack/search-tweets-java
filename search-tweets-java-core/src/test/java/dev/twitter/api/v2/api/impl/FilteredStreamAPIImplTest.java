package dev.twitter.api.v2.api.impl;

import java.util.Collections;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import dev.twitter.api.v2.api.FilteredStreamAPI;
import dev.twitter.api.v2.model.rule.Add;
import dev.twitter.api.v2.model.rule.Rules;

@RunWith(JUnit4.class)
public class FilteredStreamAPIImplTest {

  private FilteredStreamAPI api = new FilteredStreamAPIImpl();

  @Test
  public void testResponse() throws Exception {
    CountDownLatch latch = new CountDownLatch(1);
    Rules rules = new Rules();
    Add add = new Add();
    add.setTag("cats with media");
    add.setValue("cat has:media");
    rules.setAdd(Collections.singletonList(add));

    api.search(rules, null, t -> System.out.println(t));
    System.out.println("dne");
    latch.await();
  }
}
