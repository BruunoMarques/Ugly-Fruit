package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Producer {
  public String name;
  public VDMMap products = MapUtil.map();
  public VDMMap initialValues = MapUtil.map();
  public Delegation delegation = null;

  public void cg_init_Producer_1(final String nm, final VDMMap p) {

    name = nm;
    products = Utils.copy(p);
    initialValues = Utils.copy(p);
    return;
  }

  public Producer(final String nm, final VDMMap p) {

    cg_init_Producer_1(nm, Utils.copy(p));
  }

  public void setDelegation(final Delegation del) {

    delegation = del;
  }

  public void removeDelegation() {

    delegation = null;
  }

  public void addProduct(final Product product, final Number weight) {

    if (SetUtil.inSet(product, MapUtil.dom(Utils.copy(products)))) {
      Utils.mapSeqUpdate(
          products,
          product,
          ((Number) Utils.get(products, product)).doubleValue() + weight.doubleValue());
      Utils.mapSeqUpdate(
          initialValues,
          product,
          ((Number) Utils.get(initialValues, product)).doubleValue() + weight.doubleValue());

    } else {
      products = MapUtil.munion(Utils.copy(products), MapUtil.map(new Maplet(product, weight)));
      initialValues =
          MapUtil.munion(Utils.copy(initialValues), MapUtil.map(new Maplet(product, weight)));
    }

    if (!(Utils.equals(delegation, null))) {
      delegation.checkPendingUsers();
    }
  }

  public Number removeStock(final Product product, final Number weight) {

    Number ret = 0L;
    if (!(SetUtil.inSet(product, MapUtil.dom(Utils.copy(products))))) {
      return ret;

    } else {
      if (((Number) Utils.get(products, product)).doubleValue() - weight.doubleValue() < 0L) {
        {
          ret = ((Number) Utils.get(products, product));
        }

        {
          Utils.mapSeqUpdate(products, product, 0L);
        }

        return ret;

      } else {
        {
          Utils.mapSeqUpdate(
              products,
              product,
              ((Number) Utils.get(products, product)).doubleValue() - weight.doubleValue());
        }

        return weight;
      }
    }
  }

  public void restockProducts() {

    products = Utils.copy(initialValues);
  }

  public Number getWeight() {

    return sumWeight(Utils.copy(products));
  }

  public Producer() {}

  public static Number sumWeight(final VDMMap products_1) {

    if (Utils.empty(products_1)) {
      return 0L;

    } else {
      Number letBeStExp_4 = null;
      Product p = null;
      Boolean success_4 = false;
      VDMSet set_4 = MapUtil.dom(Utils.copy(products_1));
      for (Iterator iterator_4 = set_4.iterator(); iterator_4.hasNext() && !(success_4); ) {
        p = ((Product) iterator_4.next());
        success_4 = true;
      }
      if (!(success_4)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      letBeStExp_4 =
          ((Number) Utils.get(products_1, p)).doubleValue()
              + sumWeight(MapUtil.domResBy(SetUtil.set(p), Utils.copy(products_1))).doubleValue();
      return letBeStExp_4;
    }
  }

  public String toString() {

    return "Producer{"
        + "name := "
        + Utils.toString(name)
        + ", products := "
        + Utils.toString(products)
        + ", initialValues := "
        + Utils.toString(initialValues)
        + ", delegation := "
        + Utils.toString(delegation)
        + "}";
  }
}
