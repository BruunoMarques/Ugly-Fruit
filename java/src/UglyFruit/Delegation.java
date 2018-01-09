package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class Delegation {
  public String name;
  public Number userCapacity;
  public String location;
  public VDMSet users = SetUtil.set();
  public VDMSet producers = SetUtil.set();
  public VDMSet pendingUsers = SetUtil.set();
  public VDMMap userBaskets = MapUtil.map();

  public void cg_init_Delegation_1(final String nm, final String ln, final Number uc) {

    name = nm;
    location = ln;
    userCapacity = uc;
    return;
  }

  public Delegation(final String nm, final String ln, final Number uc) {

    cg_init_Delegation_1(nm, ln, uc);
  }

  public void registerUser(final User user, final Object basketSize) {

    User tmp = new User("tmp");
    tmp.setBasketSize(UglyFruit.quotes.largeQuote.getInstance());
    Boolean orResult_2 = false;

    if (users.size() >= userCapacity.longValue()) {
      orResult_2 = true;
    } else {
      Boolean orResult_3 = false;

      if (getrequiredWeight(SetUtil.union(Utils.copy(users), SetUtil.set(tmp))).doubleValue()
          > getMaxWeight().doubleValue()) {
        orResult_3 = true;
      } else {
        orResult_3 = productsPerBasket(users.size() + 1L).longValue() < 8L;
      }

      orResult_2 = orResult_3;
    }

    if (orResult_2) {
      if (!(SetUtil.inSet(user, pendingUsers))) {
        user.setBasketSize(basketSize);
        pendingUsers = SetUtil.union(Utils.copy(pendingUsers), SetUtil.set(user));
        IO.print("Pending user ");
        IO.println(user.name);
      }

    } else {
      if (SetUtil.inSet(user, pendingUsers)) {
        pendingUsers = SetUtil.diff(Utils.copy(pendingUsers), SetUtil.set(user));
      }

      user.setBasketSize(basketSize);
      user.setDelegation(this);
      users = SetUtil.union(Utils.copy(users), SetUtil.set(user));
      IO.print("Registered user ");
      IO.print(user.name);
      IO.print(" on delegation ");
      IO.println(this.name);
    }
  }

  public void removeUser(final User user) {

    user.removeDelegation();
    checkPendingUsers();
    users = SetUtil.diff(Utils.copy(users), SetUtil.set(user));
  }

  public void registerProducer(final Producer producer) {

    producer.setDelegation(this);
    producers = SetUtil.union(Utils.copy(producers), SetUtil.set(producer));
    IO.print("Registered producer ");
    IO.print(producer.name);
    IO.print(" on delegation ");
    IO.println(this.name);
    checkPendingUsers();
  }

  public VDMMap getProducts() {

    VDMMap P = MapUtil.map();
    for (Iterator iterator_6 = producers.iterator(); iterator_6.hasNext(); ) {
      Producer producer = (Producer) iterator_6.next();
      for (Iterator iterator_7 = MapUtil.dom(producer.products).iterator();
          iterator_7.hasNext();
          ) {
        Product product = (Product) iterator_7.next();
        if (SetUtil.inSet(product, MapUtil.dom(Utils.copy(P)))) {
          Utils.mapSeqUpdate(
              P,
              product,
              ((Number) Utils.get(P, product)).doubleValue()
                  + ((Number) Utils.get(producer.products, product)).doubleValue());
        } else {
          P =
              MapUtil.munion(
                  Utils.copy(P),
                  MapUtil.map(
                      new Maplet(product, ((Number) Utils.get(producer.products, product)))));
        }
      }
    }
    return Utils.copy(P);
  }

  public void createBaskets() {

    for (Iterator iterator_8 = users.iterator(); iterator_8.hasNext(); ) {
      User user = (User) iterator_8.next();
      Basket bsk = new Basket(((Object) user.basketSize));
      Number minWeight = 0L;
      Number maxWeight = 0L;
      Number variety = 0L;
      if (Utils.equals(user.basketSize, UglyFruit.quotes.smallQuote.getInstance())) {
        minWeight = 3L;
        maxWeight = 4L;
        variety = 7L;

      } else {
        minWeight = 6L;
        maxWeight = 8L;
        variety = 8L;
      }

      if (Utils.equals(user.cancelBasket, false)) {
        createBasketsAux(user, bsk, minWeight, maxWeight, variety);
      } else {
        user.setCancelBasket(false);
      }
    }
    Boolean andResult_13 = false;

    if (getrequiredWeight(Utils.copy(users)).doubleValue() < getMaxWeight().doubleValue()) {
      if (productsPerBasket(users.size()).longValue() >= 8L) {
        andResult_13 = true;
      }
    }

    if (andResult_13) {
      IO.println("No need to restock");
    } else {
      IO.println("Restocking delegation stock");
      restock();
    }
  }

  public void createBasketsAux(
      final User user,
      final Basket bsk,
      final Number minWeight,
      final Number maxWeight,
      final Number variety) {

    Number v = 0L;
    VDMSet variedProducts = SetUtil.set();
    Boolean varietyAchieved = false;
    Boolean whileCond_1 = true;
    while (whileCond_1) {
      whileCond_1 = bsk.weight.doubleValue() < minWeight.doubleValue();
      if (!(whileCond_1)) {
        break;
      }

      {
        for (Iterator iterator_9 = producers.iterator(); iterator_9.hasNext(); ) {
          Producer producer = (Producer) iterator_9.next();
          for (Iterator iterator_10 = MapUtil.dom(producer.products).iterator();
              iterator_10.hasNext();
              ) {
            Product product = (Product) iterator_10.next();
            Number w = 0.5;
            Number deletedWeight = 0L;
            Boolean andResult_16 = false;

            if (!(SetUtil.inSet(product, variedProducts))) {
              if (v.doubleValue() < variety.doubleValue()) {
                andResult_16 = true;
              }
            }

            if (andResult_16) {
              variedProducts = SetUtil.union(Utils.copy(variedProducts), SetUtil.set(product));
              v = v.doubleValue() + 1L;
            }

            if (SetUtil.inSet(product, variedProducts)) {
              deletedWeight = producer.removeStock(product, w);
              if (deletedWeight.doubleValue() > 0L) {
                bsk.addProduct(product, deletedWeight);
              }
            }

            if (v.doubleValue() >= variety.doubleValue()) {
              varietyAchieved = true;
            }

            Boolean andResult_17 = false;

            if (bsk.weight.doubleValue() >= minWeight.doubleValue()) {
              Boolean andResult_18 = false;

              if (bsk.weight.doubleValue() <= maxWeight.doubleValue()) {
                if (Utils.equals(varietyAchieved, true)) {
                  andResult_18 = true;
                }
              }

              if (andResult_18) {
                andResult_17 = true;
              }
            }

            if (andResult_17) {
              if (SetUtil.inSet(user, MapUtil.dom(Utils.copy(userBaskets)))) {
                Utils.mapSeqUpdate(userBaskets, user, bsk);
              } else {
                userBaskets =
                    MapUtil.munion(Utils.copy(userBaskets), MapUtil.map(new Maplet(user, bsk)));
              }

              if (Utils.equals(user.basketSize, UglyFruit.quotes.smallQuote.getInstance())) {
                IO.print("Small ");
              } else {
                IO.print("Large ");
              }

              IO.print("basket created for user ");
              IO.print(user.name);
              IO.print(" on delegation ");
              IO.println(this.name);
              IO.println("Basket contents: ");
              IO.println(bsk);
              IO.println("");
              return;
            }
          }
        }
      }
    }
  }

  public void restock() {

    for (Iterator iterator_11 = producers.iterator(); iterator_11.hasNext(); ) {
      Producer producer = (Producer) iterator_11.next();
      producer.restockProducts();
    }
  }

  public Number getMaxWeight() {

    return getProducerslWeight(Utils.copy(producers));
  }

  public Number getUsersWeight() {

    return getrequiredWeight(Utils.copy(users));
  }

  public Number productsPerBasket(final Number basketNum) {

    return checkVariety(getProducts(), basketNum, 0L);
  }

  public void checkPendingUsers() {

    for (Iterator iterator_12 = pendingUsers.iterator(); iterator_12.hasNext(); ) {
      User user = (User) iterator_12.next();
      registerUser(user, ((Object) user.basketSize));
    }
  }

  public Delegation() {}

  public static Number getProducerslWeight(final VDMSet producers_1) {

    if (Utils.empty(producers_1)) {
      return 0L;

    } else {
      Number letBeStExp_1 = null;
      Producer p = null;
      Boolean success_1 = false;
      VDMSet set_1 = Utils.copy(producers_1);
      for (Iterator iterator_1 = set_1.iterator(); iterator_1.hasNext() && !(success_1); ) {
        p = ((Producer) iterator_1.next());
        success_1 = true;
      }
      if (!(success_1)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      letBeStExp_1 =
          p.getWeight().doubleValue()
              + getProducerslWeight(SetUtil.diff(Utils.copy(producers_1), SetUtil.set(p)))
                  .doubleValue();
      return letBeStExp_1;
    }
  }

  public static Number getrequiredWeight(final VDMSet users_1) {

    if (Utils.empty(users_1)) {
      return 0.0;

    } else {
      Number letBeStExp_2 = null;
      User u = null;
      Boolean success_2 = false;
      VDMSet set_2 = Utils.copy(users_1);
      for (Iterator iterator_2 = set_2.iterator(); iterator_2.hasNext() && !(success_2); ) {
        u = ((User) iterator_2.next());
        success_2 = true;
      }
      if (!(success_2)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      Number ternaryIfExp_2 = null;

      if (Utils.equals(u.basketSize, UglyFruit.quotes.smallQuote.getInstance())) {
        ternaryIfExp_2 =
            4.0
                + getrequiredWeight(SetUtil.diff(Utils.copy(users_1), SetUtil.set(u)))
                    .doubleValue();
      } else {
        ternaryIfExp_2 =
            8L + getrequiredWeight(SetUtil.diff(Utils.copy(users_1), SetUtil.set(u))).doubleValue();
      }

      letBeStExp_2 = ternaryIfExp_2;

      return letBeStExp_2;
    }
  }

  public static Number checkVariety(
      final VDMMap products, final Number basketNum, final Number variety) {

    if (Utils.empty(products)) {
      return variety;

    } else {
      Number letBeStExp_3 = null;
      Product p = null;
      Boolean success_3 = false;
      VDMSet set_3 = MapUtil.dom(Utils.copy(products));
      for (Iterator iterator_3 = set_3.iterator(); iterator_3.hasNext() && !(success_3); ) {
        p = ((Product) iterator_3.next());
        success_3 = true;
      }
      if (!(success_3)) {
        throw new RuntimeException("Let Be St found no applicable bindings");
      }

      Number ternaryIfExp_3 = null;

      if (((Number) Utils.get(products, p)).doubleValue() >= basketNum.longValue()) {
        ternaryIfExp_3 =
            checkVariety(
                MapUtil.domResBy(SetUtil.set(p), Utils.copy(products)),
                basketNum,
                variety.longValue() + 1L);
      } else {
        ternaryIfExp_3 =
            checkVariety(
                MapUtil.domResBy(SetUtil.set(p), Utils.copy(products)), basketNum, variety);
      }

      letBeStExp_3 = ternaryIfExp_3;

      return letBeStExp_3;
    }
  }

  public String getName() {
	return name;
}

public Number getUserCapacity() {
	return userCapacity;
}

public String getLocation() {
	return location;
}

public VDMSet getUsers() {
	return users;
}

public VDMSet getProducers() {
	return producers;
}

public VDMSet getPendingUsers() {
	return pendingUsers;
}

public VDMMap getUserBaskets() {
	return userBaskets;
}

public String toString() {

    return "Delegation{"
        + "name := "
        + Utils.toString(name)
        + ", userCapacity := "
        + Utils.toString(userCapacity)
        + ", location := "
        + Utils.toString(location)
        + ", users := "
        + Utils.toString(users)
        + ", producers := "
        + Utils.toString(producers)
        + ", pendingUsers := "
        + Utils.toString(pendingUsers)
        + ", userBaskets := "
        + Utils.toString(userBaskets)
        + "}";
  }
}
