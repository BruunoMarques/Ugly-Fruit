package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class BasketTest {
  private Object basketSizeSmall = UglyFruit.quotes.smallQuote.getInstance();
  private Object basketSizeLarge = UglyFruit.quotes.largeQuote.getInstance();
  private Basket b1 = new Basket(((Object) basketSizeSmall));
  private Basket b2 = new Basket(((Object) basketSizeLarge));
  private Product p1 = new Product("Morango");
  private Product p2 = new Product("Batata doce");
  private Number w = 3L;
  private Number productSetw = 20L;
  private VDMMap productsSet = MapUtil.map(new Maplet(p1, 10.0), new Maplet(p2, 10.0));

  private void basketTest() {

    UtilsTest.assertTrue(Utils.equals(b1.size, basketSizeSmall));
    UtilsTest.assertTrue(Utils.equals(b2.size, basketSizeLarge));
    b1.addProduct(p1, 2L);
    b1.addProduct(p1, 1.5);
    UtilsTest.assertTrue(SetUtil.inSet(p1, MapUtil.dom(b1.products)));
    b2.addProduct(p2, 3L);
    b2.addProduct(p2, 1.5);
    UtilsTest.assertTrue(SetUtil.inSet(p1, MapUtil.dom(b1.products)));
    b1.setWeight(w);
    UtilsTest.assertTrue(Utils.equals(b1.weight, w));
    UtilsTest.assertTrue(Utils.equals(b1.getWeight(Utils.copy(productsSet)), productSetw));
  }

  public static void main() {

    new BasketTest().basketTest();
  }

  public BasketTest() {}

  public String toString() {

    return "BasketTest{"
        + "basketSizeSmall := "
        + Utils.toString(basketSizeSmall)
        + ", basketSizeLarge := "
        + Utils.toString(basketSizeLarge)
        + ", b1 := "
        + Utils.toString(b1)
        + ", b2 := "
        + Utils.toString(b2)
        + ", p1 := "
        + Utils.toString(p1)
        + ", p2 := "
        + Utils.toString(p2)
        + ", w := "
        + Utils.toString(w)
        + ", productSetw := "
        + Utils.toString(productSetw)
        + ", productsSet := "
        + Utils.toString(productsSet)
        + "}";
  }
}
