package UglyFruit;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  public String name;
  public Delegation delegation = null;
  public Object basketSize;
  public Number receivedBaskets = 0L;
  public Boolean cancelBasket = false;
  public Number paidValue = 0L;

  public void cg_init_User_1(final String nm) {

    name = nm;
    return;
  }

  public User(final String nm) {

    cg_init_User_1(nm);
  }

  public void setUsername(final String us) {

    name = us;
  }

  public void setDelegation(final Delegation del) {

    delegation = del;
  }

  public void setBasketSize(final Object bs) {

    basketSize = bs;
  }

  public void setPayingValue(final Object bs) {

    receivedBaskets = receivedBaskets.longValue() + 1L;
    if (Utils.equals(bs, UglyFruit.quotes.smallQuote.getInstance())) {
      paidValue = paidValue.doubleValue() + 3.5;
    } else {
      paidValue = paidValue.doubleValue() + 7L;
    }
  }

  public void delegationChange(final Delegation de, final Object bs) {

    delegation.removeUser(this);
    de.registerUser(this, bs);
  }

  public void removeDelegation() {

    delegation = null;
  }

  public void setCancelBasket(final Boolean val) {

    cancelBasket = val;
  }

  public String getName() {
	return name;
}

public Delegation getDelegation() {
	return delegation;
}

public Object getBasketSize() {
	return basketSize;
}

public Number getReceivedBaskets() {
	return receivedBaskets;
}

public Boolean getCancelBasket() {
	return cancelBasket;
}

public Number getPaidValue() {
	return paidValue;
}

public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", delegation := "
        + Utils.toString(delegation)
        + ", basketSize := "
        + Utils.toString(basketSize)
        + ", receivedBaskets := "
        + Utils.toString(receivedBaskets)
        + ", cancelBasket := "
        + Utils.toString(cancelBasket)
        + ", paidValue := "
        + Utils.toString(paidValue)
        + "}";
  }
}
