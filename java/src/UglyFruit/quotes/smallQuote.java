package UglyFruit.quotes;

import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class smallQuote {
  private static int hc = 0;
  private static smallQuote instance = null;

  public smallQuote() {

    if (Utils.equals(hc, 0)) {
      hc = super.hashCode();
    }
  }

  public static smallQuote getInstance() {

    if (Utils.equals(instance, null)) {
      instance = new smallQuote();
    }

    return instance;
  }

  public int hashCode() {

    return hc;
  }

  public boolean equals(final Object obj) {

    return obj instanceof smallQuote;
  }

  public String toString() {

    return "<small>";
  }
}
