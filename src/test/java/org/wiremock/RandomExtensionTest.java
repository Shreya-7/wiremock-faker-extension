package org.wiremock;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class RandomExtensionTest {

  @Test
  public void testReturnsRandomHelper() {
    RandomExtension extension = new RandomExtension();
    assertEquals("faker-helper", extension.getName());
    assertEquals(RandomHelper.class, extension.provideTemplateHelpers().get("random").getClass());
  }
}
