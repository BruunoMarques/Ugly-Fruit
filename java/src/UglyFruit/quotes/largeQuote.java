package UglyFruit.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class largeQuote {
  private static int hc = 0;
  private static largeQuote instance = null;

  public largeQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static largeQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new largeQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof largeQuote;
  }

  public String toString() {

    return "<large>";
  }
}
