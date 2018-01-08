package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ProducerTest {
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
  private Product product1 = new Product("Morango");
  private Product product2 = new Product("Batata doce");
  private Product product3 = new Product("Alface");
  private Delegation d1 = new Delegation("Delegation1", "Porto", 2L);

  private void producerTest() {

    Boolean andResult_20 = false;

    if (Utils.equals(p1.name, "Diana")) {
      if (Utils.equals(p1.products, productsSet)) {
        andResult_20 = true;
      }
    }

    UtilsTest.assertTrue(andResult_20);

    p1.setDelegation(d1);
    UtilsTest.assertTrue(Utils.equals(p1.delegation, d1));
    p1.removeDelegation();
    UtilsTest.assertTrue(Utils.equals(p1.delegation, null));
    p1.addProduct(product1, 2L);
    p1.addProduct(product1, 1.5);
    UtilsTest.assertTrue(SetUtil.inSet(product1, MapUtil.dom(p1.products)));
    d1.registerProducer(p1);
    p1.addProduct(product2, 1.5);
    UtilsTest.assertTrue(SetUtil.inSet(product2, MapUtil.dom(p1.products)));
    UtilsTest.assertTrue(Utils.equals(p1.removeStock(product1, 2.0), 2L));
    UtilsTest.assertTrue(Utils.equals(p1.removeStock(product1, 2.0), 1.5));
    UtilsTest.assertTrue(Utils.equals(p1.removeStock(product3, 2.0), 0L));
    p1.restockProducts();
    UtilsTest.assertTrue(Utils.equals(p1.products, p1.initialValues));
  }

  public static void main() {

    new ProducerTest().producerTest();
  }

  public ProducerTest() {}

  public String toString() {

    return "ProducerTest{"
        + "productsSet := "
        + Utils.toString(productsSet)
        + ", p1 := "
        + Utils.toString(p1)
        + ", product1 := "
        + Utils.toString(product1)
        + ", product2 := "
        + Utils.toString(product2)
        + ", product3 := "
        + Utils.toString(product3)
        + ", d1 := "
        + Utils.toString(d1)
        + "}";
  }
}
