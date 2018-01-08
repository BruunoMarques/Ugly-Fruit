package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class ProductTest {
  private Product p1 = new Product("Morango");

  private void productTest() {

    UtilsTest.assertTrue(Utils.equals(p1.name, "Morango"));
  }

  public static void main() {

    new ProductTest().productTest();
  }

  public ProductTest() {}

  public String toString() {

    return "ProductTest{" + "p1 := " + Utils.toString(p1) + "}";
  }
}
