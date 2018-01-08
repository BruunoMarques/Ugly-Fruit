package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class UserTest {
  private User u1 = new User("Claudia");
  private User u2 = new User("Margarida");
  private Delegation d1 = new Delegation("Delegation1", "Porto", 2L);
  private Delegation d2 = new Delegation("Delegation2", "Lisboa", 20L);
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
  private Producer p1 = new Producer("Diana", Utils.copy(productsSet));
  private Producer p2 = new Producer("Manuel", Utils.copy(productsSet));
  private Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
  private Object basketSizeLarge = UglyFruit.quotes.largeQuote.getInstance();
  private VDMMap products1 = MapUtil.map();

  private void userTest() {

    UtilsTest.assertTrue(Utils.equals(u1.name, "Claudia"));
    u1.setCancelBasket(true);
    UtilsTest.assertTrue(Utils.equals(u1.cancelBasket, true));
    u1.setCancelBasket(false);
    UtilsTest.assertTrue(Utils.equals(u1.cancelBasket, false));
    u1.setUsername("Ana");
    UtilsTest.assertTrue(Utils.equals(u1.name, "Ana"));
    d1.registerProducer(p1);
    d1.registerUser(u1, basketSizeSmall);
    UtilsTest.assertTrue(Utils.equals(u1.delegation, d1));
    u1.setBasketSize(basketSizeSmall);
    UtilsTest.assertTrue(Utils.equals(u1.basketSize, basketSizeSmall));
    u2.setBasketSize(basketSizeLarge);
    UtilsTest.assertTrue(Utils.equals(u2.basketSize, basketSizeLarge));
    u1.setPayingValue(basketSizeSmall);
    UtilsTest.assertTrue(Utils.equals(u1.paidValue, 3.5));
    u2.setPayingValue(basketSizeLarge);
    UtilsTest.assertTrue(Utils.equals(u2.paidValue, 7L));
    d2.registerProducer(p2);
    u1.delegationChange(d2, basketSizeSmall);
    UtilsTest.assertTrue(Utils.equals(u1.delegation, d2));
    u1.setCancelBasket(true);
    UtilsTest.assertTrue(Utils.equals(u1.cancelBasket, true));
    u1.setCancelBasket(false);
    UtilsTest.assertTrue(Utils.equals(u1.cancelBasket, false));
    u1.removeDelegation();
    UtilsTest.assertTrue(Utils.equals(u1.delegation, null));
  }

  public static void main() {

    new UserTest().userTest();
  }

  public UserTest() {}

  public String toString() {

    return "UserTest{"
        + "u1 := "
        + Utils.toString(u1)
        + ", u2 := "
        + Utils.toString(u2)
        + ", d1 := "
        + Utils.toString(d1)
        + ", d2 := "
        + Utils.toString(d2)
        + ", productsSet := "
        + Utils.toString(productsSet)
        + ", p1 := "
        + Utils.toString(p1)
        + ", p2 := "
        + Utils.toString(p2)
        + ", basketSizeSmall := "
        + Utils.toString(basketSizeSmall)
        + ", basketSizeLarge := "
        + Utils.toString(basketSizeLarge)
        + ", products1 := "
        + Utils.toString(products1)
        + "}";
  }
}
