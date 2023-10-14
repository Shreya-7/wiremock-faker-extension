package org.wiremock;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.is;

import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RandomHelperTest {
  RandomHelper helper;
  protected static final String FAIL_GRACEFULLY_MSG =
      "Handlebars helper should fail gracefully and show the issue directly in the response.";

  @BeforeEach
  public void init() {
    helper = new RandomHelper();
  }

  @Test
  public void rendersAMeaningfulErrorWhenExpressionIsInvalid() {
    try {
      assertThat(
          (String) renderHelperValue(helper, "something really random"),
          is("[ERROR: Unable to evaluate the expression something really random]"));
    } catch (final IOException e) {
      Assertions.fail(FAIL_GRACEFULLY_MSG);
    }
  }

  @Test
  public void returnsRandomValue() throws Exception {
    assertThat(renderHelperValue(helper, "Name.firstName"), is(any(String.class)));
  }

  private <R, C> R renderHelperValue(Helper<C> helper, C content) throws IOException {
    return (R)
        helper.apply(
            content,
            new Options(null, null, null, null, null, null, null, null, new ArrayList<String>(0)));
  }
}
