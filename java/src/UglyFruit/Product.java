package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Product {
  public String name;

  public void cg_init_Product_1(final String nm) {

    name = nm;
    return;
  }

  public Product(final String nm) {

    cg_init_Product_1(nm);
  }
  

  public String getName() {
	return name;
}

public Product() {}

  public String toString() {

    return "Product{" + "name := " + Utils.toString(name) + "}";
  }
}
