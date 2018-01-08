package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class PlatformUtilsTest {
  private VDMMap productsSet =
      MapUtil.map(
          new Maplet(new Product("Morango"), 10.0),
          new Maplet(new Product("Batata doce"), 10.0),
          new Maplet(new Product("Tomate"), 15.0),
          new Maplet(new Product("Pepino"), 20L),
          new Maplet(new Product("Couve"), 20L),
          new Maplet(new Product("Rabanete"), 20L),
          new Maplet(new Product("Ameixa"), 20L),
          new Maplet(new Product("Ananas"), 20L));
  private User u1 = new User("Claudia");
  private Producer p1 = new Producer("Diana", Utils.copy(productsSet));
  private Delegation d1 = new Delegation("Delegation1", "Porto", 2L);
  private Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();

  private void platformUtilsTest() {

    UtilsTest.assertTrue(PlatformUtils.validateUser(u1));
    UtilsTest.assertTrue(PlatformUtils.validateProducer(p1));
    d1.registerProducer(p1);
    d1.registerUser(u1, basketSizeSmall);
    UtilsTest.assertTrue(Utils.equals(PlatformUtils.validateUser(u1), false));
    UtilsTest.assertTrue(Utils.equals(PlatformUtils.validateProducer(p1), false));
  }

  public static void main() {

    new PlatformUtilsTest().platformUtilsTest();
  }

  public PlatformUtilsTest() {}

  public String toString() {

    return "PlatformUtilsTest{"
        + "productsSet := "
        + Utils.toString(productsSet)
        + ", u1 := "
        + Utils.toString(u1)
        + ", p1 := "
        + Utils.toString(p1)
        + ", d1 := "
        + Utils.toString(d1)
        + ", basketSizeSmall := "
        + Utils.toString(basketSizeSmall)
        + "}";
  }
}
