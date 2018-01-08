package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Basket {
  public Object size;
  public Number weight = 0L;
  public VDMMap products = MapUtil.map();
  public User user = null;

  public void cg_init_Basket_1(final Object sz) {

    size = sz;
    return;
  }

  public Basket(final Object sz) {

    cg_init_Basket_1(sz);
  }

  public void addProduct(final Product product, final Number w) {

    VDMMap pds = Utils.copy(products);
    if (!(SetUtil.inSet(product, MapUtil.dom(Utils.copy(products))))) {
      pds = MapUtil.munion(Utils.copy(pds), MapUtil.map(new Maplet(product, w)));
    } else {
      Utils.mapSeqUpdate(
          pds, product, ((Number) Utils.get(pds, product)).doubleValue() + w.doubleValue());
    }

    if (Utils.equals(size, UglyFruit.quotes.smallQuote.getInstance())) {
      if (getWeight(Utils.copy(pds)).doubleValue() <= 4L) {
        if (SetUtil.inSet(product, MapUtil.dom(Utils.copy(products)))) {
          Utils.mapSeqUpdate(
              products,
              product,
              ((Number) Utils.get(products, product)).doubleValue() + w.doubleValue());
        } else {
          products = Utils.copy(pds);
        }
      }

    } else if (Utils.equals(size, UglyFruit.quotes.largeQuote.getInstance())) {
      if (getWeight(Utils.copy(pds)).doubleValue() <= 8L) {
        if (SetUtil.inSet(product, MapUtil.dom(Utils.copy(products)))) {
          Utils.mapSeqUpdate(
              products,
              product,
              ((Number) Utils.get(products, product)).doubleValue() + w.doubleValue());
        } else {
          products = Utils.copy(pds);
        }
      }
    }

    setWeight(getWeight(Utils.copy(products)));
  }

  public void setWeight(final Number w) {

    weight = w;
  }

  public Number getWeight(final VDMMap pds) {

    Number tmp = 0L;
    for (Iterator iterator_5 = MapUtil.dom(Utils.copy(pds)).iterator(); iterator_5.hasNext(); ) {
      Product p = (Product) iterator_5.next();
      tmp = tmp.doubleValue() + ((Number) Utils.get(pds, p)).doubleValue();
    }
    return tmp;
  }

  public Basket() {}

  public String toString() {

    return "Basket{"
        + "size := "
        + Utils.toString(size)
        + ", weight := "
        + Utils.toString(weight)
        + ", products := "
        + Utils.toString(products)
        + ", user := "
        + Utils.toString(user)
        + "}";
  }
}
