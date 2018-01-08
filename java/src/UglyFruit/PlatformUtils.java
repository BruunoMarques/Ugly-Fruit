package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PlatformUtils {
  public PlatformUtils() {}

  public static Boolean validateUser(final User user) {

    if (Utils.equals(user.delegation, null)) {
      return true;

    } else {
      return false;
    }
  }

  public static Boolean validateProducer(final Producer producer) {

    if (Utils.equals(producer.delegation, null)) {
      return true;

    } else {
      return false;
    }
  }

  public String toString() {

    return "PlatformUtils{}";
  }
}
