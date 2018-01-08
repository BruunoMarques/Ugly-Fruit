package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class DelegationTest {
  private User u1 = new User("Claudia");
  private User u2 = new User("Margarida");
  private User u3 = new User("Bruno");
  private User u4 = new User("Rita");
  private Delegation d1 = new Delegation("Delegation1", "Porto", 2L);
  private Delegation d2 = new Delegation("Delegation2", "Lisboa", 20L);
  private VDMMap productsSet =
      MapUtil.map(
          new Maplet(new Product("Morango"), 2L),
          new Maplet(new Product("Batata doce"), 2L),
          new Maplet(new Product("Tomate"), 15.0),
          new Maplet(new Product("Pepino"), 20L),
          new Maplet(new Product("Couve"), 20L),
          new Maplet(new Product("Rabanete"), 20L),
          new Maplet(new Product("Ameixa"), 20L),
          new Maplet(new Product("Ananas"), 20L));
  private Producer p1 = new Producer("Diana", Utils.copy(productsSet));
  private Producer p2 = new Producer("Manuel", Utils.copy(productsSet));
  private Producer p3 = new Producer("Carlos", MapUtil.map(new Maplet(new Product("Kiwi"), 40L)));
  private Producer p4 = new Producer("Ricardo", Utils.copy(productsSet));
  private Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
  private Object basketSizeLarge = UglyFruit.quotes.largeQuote.getInstance();

  private void delegationTest() {

    UtilsTest.assertTrue(Utils.equals(d1.name, "Delegation1"));
    UtilsTest.assertTrue(Utils.equals(d1.location, "Porto"));
    UtilsTest.assertTrue(Utils.equals(d1.userCapacity, 2L));
    d1.registerProducer(p1);
    UtilsTest.assertTrue(SetUtil.inSet(p1, d1.producers));
    d1.registerUser(u1, basketSizeSmall);
    UtilsTest.assertTrue(SetUtil.inSet(u1, d1.users));
    d1.registerUser(u2, basketSizeSmall);
    d1.registerUser(u3, basketSizeSmall);
    UtilsTest.assertTrue(SetUtil.inSet(u3, d1.pendingUsers));
    d1.removeUser(u2);
    d1.registerUser(u3, basketSizeSmall);
    UtilsTest.assertTrue(Utils.empty(d1.pendingUsers));
    d1.removeUser(u1);
    UtilsTest.assertTrue(!(SetUtil.inSet(u1, d1.users)));
    UtilsTest.assertTrue(Utils.equals(d1.getProducts(), productsSet));
    d1.registerProducer(p2);
    UtilsTest.assertTrue(!(Utils.equals(d1.getProducts(), productsSet)));
    d1.registerUser(u1, basketSizeLarge);
    d1.createBaskets();
    UtilsTest.assertTrue(!(Utils.empty(d1.userBaskets)));
    u1.setCancelBasket(true);
    d1.createBaskets();
    UtilsTest.assertTrue(!(Utils.empty(d1.userBaskets)));
    d2.registerProducer(p3);
    d2.registerProducer(p4);
    d2.registerUser(u4, basketSizeLarge);
    d2.createBaskets();
    d2.createBaskets();
    UtilsTest.assertTrue(!(Utils.empty(d2.userBaskets)));
    UtilsTest.assertTrue(!(Utils.equals(d1.productsPerBasket(d1.users.size()), 0L)));
    UtilsTest.assertTrue(
        Utils.equals(d1.checkVariety(Utils.copy(productsSet), d1.users.size(), 0L), 8L));
    UtilsTest.assertTrue(
        Utils.equals(
            d1.checkVariety(MapUtil.map(new Maplet(new Product("Kiwi"), 0.5)), d1.users.size(), 0L),
            0L));
  }

  public static void main() {

    new DelegationTest().delegationTest();
  }

  public DelegationTest() {}

  public String toString() {

    return "DelegationTest{"
        + "u1 := "
        + Utils.toString(u1)
        + ", u2 := "
        + Utils.toString(u2)
        + ", u3 := "
        + Utils.toString(u3)
        + ", u4 := "
        + Utils.toString(u4)
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
        + ", p3 := "
        + Utils.toString(p3)
        + ", p4 := "
        + Utils.toString(p4)
        + ", basketSizeSmall := "
        + Utils.toString(basketSizeSmall)
        + ", basketSizeLarge := "
        + Utils.toString(basketSizeLarge)
        + "}";
  }
}
