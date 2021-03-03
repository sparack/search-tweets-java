package dev.twitter.api.v2.parser.impl;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import lombok.Getter;
import lombok.Setter;

@RunWith(JUnit4.class)
public class JacksonParserTest {

  static class Person {
    private String name;
    private int age;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public int getAge() {
      return age;
    }

    public void setAge(int age) {
      this.age = age;
    }
  }

  @Getter @Setter
  static class PersonLombok {
    private String name;
    private int age;
  }

  JacksonParser parser = new JacksonParser();

  @Test
  public void testSimpleObjectToJson() throws Exception {
    Person person = new Person();
    person.setName("Peter");
    person.setAge(25);
    String json = parser.objectToJson(person);
    JSONAssert.assertEquals("{\"age\": 25, \"name\": \"Peter\"}", json, JSONCompareMode.STRICT);
  }

  @Test
  public void testLombokObjectToJson() throws Exception {
    PersonLombok person = new PersonLombok();
    person.setName("Peter");
    person.setAge(25);
    String json = parser.objectToJson(person);
    JSONAssert.assertEquals("{\"age\": 25, \"name\": \"Peter\"}", json, JSONCompareMode.STRICT);
  }

  @Test
  public void testSimpleJsonToObject() throws Exception {
    Person person = parser.jsonToObject("{\"age\": 25, \"name\": \"Peter\"}", Person.class);
    assertEquals(25, person.getAge());
    assertEquals("Peter", person.getName());
  }

  @Test
  public void testLombokJsonToObject() throws Exception {
    PersonLombok person = parser.jsonToObject("{\"age\": 25, \"name\": \"Peter\"}", PersonLombok.class);
    assertEquals(25, person.getAge());
    assertEquals("Peter", person.getName());
  }

}
